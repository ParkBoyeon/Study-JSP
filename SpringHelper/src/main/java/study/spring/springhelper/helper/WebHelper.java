package study.spring.springhelper.helper;

import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractView;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;
import study.spring.springhelper.model.UploadItem;

@Slf4j
public class WebHelper {
    /** 기본 인코딩 타입 설정 */
    private String encType;

    /** JSP의 request 내장 객체 */
    // --> import javax.servlet.http.HttpServletRequest;
    private HttpServletRequest request;

    /** JSP의 response 내장 객체 */
    // --> import javax.servlet.http.HttpServletResponse;
    private HttpServletResponse response;
    
    /** 업로드 된 결과물이 저장될 폴더 */
    private String uploadDir;
    
    /** 업로드 된 파일이 식별될 URL 경로 */
    private String uploadPath;

    // ----------- 싱글톤 객체 생성 시작 ----------
    /**
     * 싱글톤 객체가 생성될 때 호출되는 메서드로 JSP의 주요 내장객체를 멤버변수에 연결한다.
     *
     * @param request
     * @param response
     */
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request; // JSP 내장객체 참조하기
        this.response = response; // JSP 내장객체 참조하기

        String methodName = request.getMethod(); // GET방식인지, POST방식인지 조회한다.
        String url = request.getRequestURL().toString(); // 현재 URL을 획득한다.
        String queryString = request.getQueryString(); // URL에서 "?"이후의 GET파라미터 문자열을 모두 가져온다.

        if (queryString != null) { // 가져온 값이 있다면 URL과 결합하여 완전한 URL을 구성한다.
            url = url + "?" + queryString;
        }

        log.debug(String.format("[%s] %s", methodName, url)); // 획득한 정보를 로그로 표시한다.

        /** 내장객체 초기화 -> utf-8 설정 */
        // --> import java.io.UnsupportedEncodingException;
        try {
            this.request.setCharacterEncoding(this.encType);
            this.response.setCharacterEncoding(this.encType);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /** Getter, Setter 메서드 */
    public String getEncType() { return encType; }

    public void setEncType(String encType) { this.encType = encType; }

    public HttpServletRequest getRequest() { return request; }

    public void setRequest(HttpServletRequest request) { this.request = request; }

    public HttpServletResponse getResponse() { return response; }

    public void setResponse(HttpServletResponse response) { this.response = response; }
    
    

    public String getUploadDir() { return uploadDir; }

	public void setUploadDir(String uploadDir) { this.uploadDir = uploadDir; }

	public String getUploadPath() { return uploadPath; }

	public void setUploadPath(String uploadPath) { this.uploadPath = uploadPath; }
	
	
	/**
	 * 업로드 폴더 하위에 저장되어 있는 파일이름을 전달받아 Web에서 접근 가능한 경로로 리턴한다.
	 */
	public String getUploadUrl(String filePath) {
		// URL상의 업로드 폴더와 파일 이름을 결합하여 파일 객체 생성
		File f = new File(this.uploadPath, filePath);
		// 결합된 경로 추출
		String path = f.getPath();
		// window의 경우 경로 구문을 역슬래시로 하는데, 이는 웹에 노출할 수 있는 형태가 아니므로 
		// 역슬래시를 슬래시로 변환하여 반환한다.
		return path.replace("\\", "/");
		
	}
	
	
	/**
	 * 컨트롤러로부터 업로드 된 파일의 정보를 전달받아 지정된 위치에 저장한다. 
	 * 이 때, 파일 덮어쓰기를 방지하기 위해 파일의 이름을 변경하고 이름 중복 검사를 수행한다.
	 * @param multipartFile				업로드 된 파일 정보
	 * @return							파일 정보를 담고 있는 객체
	 * @throws NullPointerException		업로드 된 파일이 없는 경우
	 * @throws Exception				파일 저장에 실패한 경우
	 */
	public UploadItem saveMultipartFile(MultipartFile multipartFile) throws NullPointerException, Exception {
		UploadItem item = null;
		
		/** 1) 업로드 파일 저장하기 */
		// 파일의 원본 이름 추출
		String originName = multipartFile.getOriginalFilename();
		
		// 업로드 된 파일이 존재하는지 확인한다.
		if(originName.isEmpty()) {
			throw new NullPointerException("업로드 된 파일이 없음");
		}
		
		/** 2) 동일한 이름의 파일이 존재하는지 검사한다. */
		// 파일의 원본 이름에서 확장자만 추출
		String ext = originName.substring(originName.lastIndexOf("."));
		String fileName = null;		// 웹 서버에 저장될 파일이름
		File targetFile = null;		// 저장된 파일 정보를 담기 위한 File 객체
		int count = 0;				// 중복된 파일 수
		
		// 일단 무한루프
		while (true) {
			// 저장될 파일 이름 --> 현재시각 + 카운트값 + 확장자
			fileName = String.format("%d%d%s", System.currentTimeMillis(), count, ext);
			// 업로드 파일이 저장될 폴더 + 파일이름으로 파일객체를 생성한다.
			targetFile = new File(this.uploadDir, fileName);
			
			// 동일한 이름의 파일이 없다면 반복 중단.
			if(!targetFile.exists()) {
				break;
			}
			
			// if문을 빠져나올 경우 중복된 이름의 파일이 존재한다는 의미이므로 count를 1증가
			count++;
		} // end while
		
		/** 3) 업로드 된 파일을 결정된 파일 경로로 저장 */
		multipartFile.transferTo(targetFile);
		
		/** 4) 업로드 경로 정보 처리하기 */
		// 복사된 파일의 절대경로를 추출한다.
		// --> 운영체제 호환을 위해 역슬래시를 슬래시로 변환한다.
		String absPath = targetFile.getAbsolutePath().replace("\\", "/");
		
		// 절대경로에서 이미 root-context에 지정되어 있는 업로드 폴더 경로를 삭제한다.
		String filePath = absPath.replace(this.uploadDir, "");
		
		// 리턴할 정보를 구성한다.
		item = new UploadItem();
		item.setContentType(multipartFile.getContentType());
		item.setFieldName(multipartFile.getName());
		item.setFileSize(multipartFile.getSize());
		item.setOriginName(originName);
		item.setFilePath(filePath);
		
		// WebHelper에 의해 생성된 업로드 경로는 서버상의 위치일 뿐 웹상에 노출될 수 있는 형태는 아니다.
		// View를 통해 웹 상에 노출하기 위해서는 업로드 위치늬 URL PATH를 덧붙인 현태로 경로를 가공해야한다.
		String fileUrl = this.getUploadUrl(filePath);
		item.setFileUrl(fileUrl);
		
		return item;
		
	}
	
	

	/**
     * 메시지 표시 후, 페이지를 지정된 곳으로 이동한다.
     *
     * @param url - 이동할 페이지의 URL, Null일 경우 이전페이지로 이동
     * @param msg - 화면에 표시할 메시지. Null일 경우 표시 안함
     * @return ModelAndView
     */
    public ModelAndView redirect(String url, String msg) {

        // 획득한 정보를 로그로 표시한다.
        log.debug(String.format(" --> [redirect] %s >> %s", url, msg));

        // 가상의 View로 만들기 위한 HTML 태그 구성
        String html = "<!doctype html>";
        html += "<html>";
        html += "<head>";
        html += "<meta charset='" + this.encType + "'>";

        // 메시지 표시
        if (msg != null) {
            html += "<script type='text/javascript'>alert('" + msg + "');</script>";
        }

        // 페이지 이동
        if (url != null) {
            html += "<meta http-equiv='refresh' content='0; url=" + url + "' />";
        } else {
            html += "<script type='text/javascript'>history.back();</script>";
        }

        html += "</head>";
        html += "<body></body>";
        html += "</html>";

        return this.virtualView(html);
    }

    /**
     * 파라미터로 받은 내용을 가상의 View로 생성후 리턴한다.
     * 브라우저에게 전달할 HTML,CSS,JS 조합을 출력하기 위해 사용한다.
     * @param body - 브라우저에게 전달할 HTML,CSS,JS 조합 문자열
     * @return ModelAndView
     */
    public ModelAndView virtualView(final String body) {
        /** 가상의 View를 익명 클래스 방식으로 생성하여 리턴 */
        // --> import org.springframework.web.servlet.View;
        // --> import org.springframework.web.servlet.view.AbstractView;
        View view = new AbstractView() {
            @Override
            protected void renderMergedOutputModel(Map<String, Object> map,
                    HttpServletRequest request, HttpServletResponse response) throws Exception {
                PrintWriter out = response.getWriter();
                out.println(body);
                out.flush();
            }
        };

        // 가상의 뷰를 리턴한다.
        return new ModelAndView(view);
    }
    
    /**
     * 전달받은 Map객체에 rt와 pubDate값을 추가한 후 리턴한다.
     *
     * @param statusCode    HTTP 결과코드 (200, 404, 500 등)
     * @param rt            결과메시지 (성공일 경우 OK, 그 밖의 경우 에러메시지)
     * @param data          JSON으로 변환할 데이터 컬렉션
     *
     * @return Map<String, Object>
     */
    public Map<String, Object> getJsonData(int statusCode, String rt, Map<String, Object> data) {
        /** 1) JSON 형식 출력을 위한 HTTP 헤더 설정 */
        // JSON 형식임을 명시함
        response.setContentType("application/json");

        // HTTP 상태 코드 설정 (200, 404, 500 등)
        response.setStatus(statusCode);

        // CrossDomain에 의한 접근 허용
        // (보안에 좋지 않기 때문에 이 옵션을 허용할 경우 인증키 등의 보안장치가 필요함)
        // 여기서는 실습을 위해 허용함
        this.response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        this.response.setHeader("Access-Control-Max-Age", "3600");
        this.response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        this.response.setHeader("Access-Control-Allow-Origin", "*");

        /** 2) 파라미터로 전달된 Map 객체에 rt와 pubDate값을 추가한 새로운 Map 생성하기 */
        // JSON 생성일시를 위한 현재 시각 문자열 만들기
        Calendar c = Calendar.getInstance();
        String pubDate = String.format("%04d-%02d-%02d %02d:%02d:%02d",
                            c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH),
                            c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND));

        // JSON 구성을 위한 컬렉션 객체 생성
        Map<String, Object> map = new HashMap<String, Object>();

        // 결과코드 추가
        map.put("rt", rt);

        // 생성일시 추가
        map.put("pubDate", pubDate);

        // data가 전달되었다면 map에 병합한다.
        if (data != null) {
            map.putAll(data);
        }
        
        return map;
    }

    /**
     * JSON 형식으로 결과 메시지를 리턴하기 위한 메서드 오버로드
     * HTTP 상태코드는 200으로 설정하고, 결과 메시지는 "OK"라는 값을 설정하여
     * getJsonData(int statusCode, String rt, Map<String, Object> data)
     * 를 호출한다.
     *
     * JSON으로 표현하고자 하는 컬렉션이 있을 때 사용하는 가장 일반적인 메서드이다.
     *
     * @param data          JSON으로 변환할 데이터 컬렉션
     *
     * @return Map<String, Object>
     */
    public Map<String, Object> getJsonData(Map<String, Object> data) {
        return this.getJsonData(200, "OK", data);
    }

    /**
     * JSON 형식으로 결과 메시지를 리턴하기 위한 메서드 오버로드
     * HTTP 상태코드는 200으로 설정하고, 결과 메시지는 "OK"라는 값을 설정하여
     * getJsonData(int statusCode, String rt, Map<String, Object> data)
     * 를 호출한다.
     *
     * JSON으로 표현하고자 하는 컬렉션이 없지만 처리 결과는 성공인 경우 사용한다.
     * ex) 데이터 입력, 수정, 삭제 등을 수행하고 특별히 조회 결과를 반환하지 않을 경우
     *
     * @return Map<String, Object>
     */
    public Map<String, Object> getJsonData() {
        return this.getJsonData(200, "OK", null);
    }

    /**
     * JSON 형식으로 에러 메시지를 리턴한다.
     * HTTP 상태코드는 500으로 설정하고, 결과 메시지는 파라미터로 전달되는 값을 설정하여 
     * JSON 문자열을 리턴한다.
     *
     * Service 구현체에서 예외 발생시 전달하는 에러 메시지 등을 JSON으로 표현할 때 사용.
     *
     * @param rt
     *
     * @return Map<String, Object>
     */
    public Map<String, Object> getJsonError(String rt) {
        return this.getJsonData(500, rt, null);
    }

    /**
     * JSON 형식으로 잘못된 접근에 대한 경고 메시지를 리턴한다.
     * HTTP 상태코드는 400으로 설정하고, 결과 메시지는 파라미터로 전달되는 값을 설정하여 
     * JSON 문자열을 리턴한다.
     *
     * 파라미터 점검 과정에서 유효성 검증에 통과하지 못한 경우 사용.
     *
     * @param rt
     *
     * @return Map<String, Object>
     */
    public Map<String, Object> getJsonWarning(String rt) {
        return this.getJsonData(400, rt, null);
    }
    
    
    public String createThumbnail(String path, int width, int height, boolean crop) throws Exception {
    	
    	/** 1) 썸네일 생성 정보를 로그로 기록하기 */
    	log.debug(String.format("[Thumbnail] path: %s, size: %d%d, crop: %s",  path, width, height, String.valueOf(crop)));
    	
    	/** 2) 저장될 썸네일 이미지의 경로 문자열 만들기 */
    	File loadFile = new File(this.uploadDir, path);		// 원본 파일의 전체 경로 --> 업로드 폴더(상수값) + 파일명
    	String dirPath = loadFile.getParent(); 				// 전체 경로에서 파일이 위치한 폴더 경로 분히
    	String fileName = loadFile.getName();				// 전체 경로에서 파일 이름만 분리
    	int p = fileName.lastIndexOf(".");					// 파일이름에서 마지막 점(.)의 위치
    	String name = fileName.substring(0, p);				// 파일명 분리 -> 파일이름에서 마지막 점의 위치 전까지
    	String ext = fileName.substring(p + 1);				// 확장자 분리 -> 파일이름에서 마지막 점의 위치 다음부터 끝까지
    	String prefix = crop ? "_crop_" : "_resize_";		// 크롭인지 리사이즈인지에 대한 문자열
    	
    	// 최종 파일이름을 구성한다. --> 원본이름 + 크롭여부 + 요청된 사이즈
    	// -> ex) myphoto.jsp --> myphoto_resize_320x240.jpg
    	String thumbName = name + prefix + width + "x" + height + "." + ext;
    	
    	File f = new File(dirPath, thumbName);		// 생성될 썸네일 파일 객체 --> 업로드폴더 + 썸네일이름
    	String saveFile = f.getAbsolutePath();		// 생성될 썸네일 파일 객체로부터 절대경로 추출 (리턴할 값)
    	
    	// 생성될 썸네일 이미지의 경로를 로그로 기록
    	log.debug(String.format("[Thumbnail] saveFile: %s", saveFile));
    	
    	/** 3) 썸네일 이미지 생성하고 최종 경로 리턴 */
    	// 해당 결로에 이미지가 없는 경우만 수행
    	if (!f.exists()) {
    		// 원본 이미지 가져오기
    		Builder<File> builder = Thumbnails.of(loadFile);
    		// 이미지 크롭 여부 파라미터에 따라 크롭 옵션을 지정한다. 
    		if(crop == true) {
    			builder.crop(Positions.CENTER);
    		}
    		
    		builder.size(width, height);			// 축소할 사이즈 지정
    		builder.useExifOrientation(true);		// 세로로 촬영된 사진을 회전시킴
    		builder.outputFormat(ext);				// 파일의 확장명 지정
    		builder.toFile(saveFile);				// 저장할 파일경로 지정
    	}
    	
    	// 최종적으로 생성된 경로에서 업로드 폴더까지의 경로를 제고한다.
    	saveFile = saveFile.replace("\\", "/").replace(this.uploadDir, "");
    	
		return saveFile;
    	
    }
    
    
}
