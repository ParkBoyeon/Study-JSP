package study.jsp.model1.helper;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebHelper {
	
	/** 기본 인코딩 타입 설정 */
	private String encType = "UTF-8";
	
	
	/** JSP의 request 내장 객체 */
	private HttpServletRequest request;
	
	/** JSP의 response 내장 객체 */
	private HttpServletResponse response;
	
	
	// ----- 싱글톤 객체 생성 시작 -----
	
	private static WebHelper current;
	
	// JSP의 내장 객체는 일반 JAVA 클래스가 생성할 수 없기 때문에
	// JSP의 페이지로부터 request와 response 객체를 전달받아야 한다.
	public static WebHelper getInstance(HttpServletRequest request, HttpServletResponse response) {
		if (current == null) {
			current = new WebHelper();
		}
		
		// JSP 내장 객체를 연결하기 위한 메서드를 호출한다.
		current.init(request, response);
		return current;
	}
	
	public static void freeInstance() {
		current = null;
	}
	
	private WebHelper() {
		super();
	}
	
	
	/**
	 * 싱글톤 객체가 생성될 때 호출되는 메서드로 JSP의 주요 내장객체를 멤버변수에 연결한다.
	 * 
	 * @param request
	 * @param response
	 */
	public void init(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;			// JSP 내장객체 참조하기
		this.response = response;		// JSP 내장객체 참조하기
		
		String methodName = request.getMethod();			// GET방식인지, POST방식인지 조회한다.
		String url = request.getRequestURL().toString();	// 현재 URL을 획득한다.
		String queryString = request.getQueryString();		// URL에서 "?"이후의 GET파라미터 문자열을 모두 가져온다.
		
		if(queryString != null) {				// 가져온 값이 있다면 URL과 결합하여 완전한 URL을 구성한다.
			url = url + "?" + queryString;
		}
		
		log.debug(String.format("[%s] %s", methodName, url));	// 획득한 정보를 로그로 표시한다.
		
		/** 내장객체 초기화 -> utf-8 설정 */
		try {
			this.request.setCharacterEncoding(this.encType);
			this.response.setCharacterEncoding(this.encType);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/** Getter, Setter 메서드 */
	public String getEncType() {
		return encType;
	}

	public void setEncType(String encType) {
		this.encType = encType;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	
	
	/**
	 * 메시지 표시 후, 페이지를 지정된 곳으로 이동한다.
	 * 
	 * @param url - 이동할 페이지의 URL, Null일 경우 이전페이지로 이동
	 * @param msg - 화면에 표시할 메시지, Null일 경우 표시 안함
	 */
	public void redirect(String url, String msg) {
		
		// 획득한 정보를 로그로 표시한다.
		log.debug(String.format("--> [redirect] %s >> %s", url, msg));
		
		// 가상의 View로 만들기 위한 HTML 태그 구성
		String html = "<!doctype html>";
		html += "<html>";
		html += "<head>";
		html += "<meta charset='" + this.encType +"'>";
		
		// 메시지 표시
		if (msg != null) {
			html += "<script type='text/javascript'>alert('" + msg + "');</script>";
		}
		
		// 페이지 이동
		if (url != null) {
			html += "<meta http-equiv='refresh' content='0; url=" + url +"' />";
		} else {
			html += "<script type='text/javascript'>history.back();</script>";
		}
		
		html += "</head>";
		html += "<body></body>";
		html += "</html>";
		
		// 구성된 HTML을 출력한다.
		try {
			// 브라우저에 결과값을 출력하기 위한 out객체를 생성한다. (예외처리 필요)
			PrintWriter out = this.response.getWriter();
			
			// 준비된 html 태그를 출력한다.
			out.print(html);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
