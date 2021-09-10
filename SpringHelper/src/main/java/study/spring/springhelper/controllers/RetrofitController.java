package study.spring.springhelper.controllers;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import retrofit2.Call;
import retrofit2.Retrofit;
import study.spring.springhelper.helper.RetrofitHelper;
import study.spring.springhelper.helper.WebHelper;
import study.spring.springhelper.model.Image;
import study.spring.springhelper.model.SearchDailyBoxOfficeList;
import study.spring.springhelper.model.SearchDailyBoxOfficeList.BoxOfficeResult.DailyBoxOfficeList;
import study.spring.springhelper.service.ApiKakaoSearchService;
import study.spring.springhelper.service.ApiKobisService;

@Controller
public class RetrofitController {
    @Autowired
    WebHelper webHelper;
    
    @Autowired
    RetrofitHelper retrofitHelper;
    
    /** 영화진흥원 연동 결과를 그래프로 표시하는 페이지 */
    @RequestMapping(value = "/retrofit/daily_box_office.do", method = RequestMethod.GET)
    public String dailyBoxOffice(Model model, @RequestParam(required=false) String date) {   
        /** 1) API 연동 객체 생성 */     
        // Retrofit 객체 생성
        // --> import retrofit2.Retrofit;
        // --> import study.spring.springhelper.service.ApiKobisService;
        Retrofit retrofit = retrofitHelper.getRetrofit(ApiKobisService.BASE_URL);
        
        // Service 객체를 생성한다. 구현체는 Retrofit이 자동으로 생성해 준다.
        ApiKobisService apiKobisService = retrofit.create(ApiKobisService.class);

        /** 2) 검색 파라미터 처리 */
        // 최초 접속시 검색어가 없다면 Calendar 클래스를 사용하여 하루 전 날짜 값을 yyyy-mm-dd 형식으로 생성한다.
        if (date == null) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, -1);

            date = String.format("%04d-%02d-%02d", 
                                 c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
        }

        /** 3) 영화 진흥원에 API 요청 */
        // 영화진흥원 API에 전달할 날짜 형식은 yyyymmdd 이므로 준비된 날짜 문자열에서 "-"를 제거한다.
        String targetDt = date.replace("-", "");
        
        // 영화진흥원 OpenAPI를 통해 검색결과 받아오기
        Call<SearchDailyBoxOfficeList> call = apiKobisService.getSearchDailyBoxOfficeList(targetDt);
        SearchDailyBoxOfficeList searchDailyBoxOfficeList = null;
        try {
            searchDailyBoxOfficeList = call.execute().body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 변수명이 너무 길어서 별도로 참조변수를 만들었다.
        List<DailyBoxOfficeList> list = null;
        
        // 검색 결과가 있다면 list만 추출한다.
        if (searchDailyBoxOfficeList != null) {
            list = searchDailyBoxOfficeList.getBoxOfficeResult().getDailyBoxOfficeList();    
        }
        
        /** 4) 그래프 출력을 위한 JS 코드에서 사용할 문자열 만들기 */
        int size = list.size();                             // 리스트의 길이 저장
        String[] audiCnt = new String[size];                // 관람객 수를 저장할 배열
        String[] movieNm = new String[size];                // 영화 제목을 저장할 배열
        
        for (int i=0; i<size; i++) {                        // 길이만큼 반복
            DailyBoxOfficeList item = list.get(i);          // List에서 i번째 항목 추출
            movieNm[i] = "'" + item.getMovieNm() + "'";     // 영화제목을 배열에 원소로 저장
            audiCnt[i] = String.valueOf(item.getAudiCnt()); // 관람객수를 배열에 원소로 저장
        }
        
        String movieNmStr = String.join(", ", movieNm);     // 영화제목 배열을 콤마(,)를 기준으로 문자열로 연결
        String audiCntStr = String.join(", ", audiCnt);     // 관람객수 배열을 콤마(,)를 기준으로 문자열로 연결
        
        /** 5) View 처리 */
        // 사용자가 dropdown에 지정한 값(yyyy-mm-dd)
        model.addAttribute("date", date);
        // 영화진흥원에 전달하기 위한 검색 날짜 값 (yyyymmdd)
        model.addAttribute("targetDt", targetDt);
        // 박스오피스 순위 목록
        model.addAttribute("list", list);
        // 그래프에 적용할 영화 제목 배열
        model.addAttribute("movieNmStr", movieNmStr);
        // 그래프에 적용할 관람객 수 배열
        model.addAttribute("audiCntStr", audiCntStr);
        
        // View 경로 지정
        return "retrofit/daily_box_office";
    }

    /** 카카오 이미지 검색 결과를 연동하는 페이지 */
    @RequestMapping(value = "/retrofit/kakao_image_search.do", method = RequestMethod.GET)
    public String kakaoImageSearch(Model model, @RequestParam(required=false, defaultValue="") String query) {
        /** 1) API 연동 객체 생성 */
        // Retrofit 객체 생성
        // --> import retrofit2.Retrofit;
        // --> import study.spring.springhelper.service.ApiKakaoSearchService;
        Retrofit retrofit = retrofitHelper.getRetrofit(ApiKakaoSearchService.BASE_URL);

        // Service 객체를 생성한다. 구현체는 Retrofit이 자동으로 생성해 준다.
        ApiKakaoSearchService apiKakaoSearchService = retrofit.create(ApiKakaoSearchService.class);

        /** 2) API 연동 */
        // 검색 결과를 저장할 Beans 객체 선언
        // --> import study.spring.springhelper.model.Image;
        Image image = null;

        // 검색어가 존재할 경우 KakaoOpenAPI를 통해 검색 결과 받아옴.
        if (!query.equals("")) {
            Call<Image> call = apiKakaoSearchService.getImage(query, 1, 50);

            try {
                image = call.execute().body();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        /** 3) View 처리 */
        model.addAttribute("query", query);
        model.addAttribute("image", image);
        return "retrofit/kakao_image_search";
    }
}
