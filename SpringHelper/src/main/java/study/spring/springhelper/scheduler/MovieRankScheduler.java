package study.spring.springhelper.scheduler;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Retrofit;
import study.spring.springhelper.helper.RetrofitHelper;
import study.spring.springhelper.model.SearchDailyBoxOfficeList;
import study.spring.springhelper.model.SearchDailyBoxOfficeList.BoxOfficeResult.DailyBoxOfficeList;
import study.spring.springhelper.service.ApiKobisService;
import study.spring.springhelper.service.MovieRankService;

@Slf4j
@Controller
public class MovieRankScheduler {
    
    /** 통신처리에 필요한 객체 주입 받기 */
    @Autowired RetrofitHelper retrofitHelper;

    /** 데이터베이스 연동에 필요한 서비스 객체 주입받기 */
    @Autowired MovieRankService movieRankService;

    /**
     * 영화진흥원 API를 수집하여 DB에 저장하기 위한 메서드
     */
   // @ResponseBody
   // @RequestMapping(value = "/springhelper/move_rank_scheduler", method = RequestMethod.GET)
    public void collectMovieRank() {
        /** 1) API 연동 객체 생성 */     
        // Retrofit 객체 생성
        Retrofit retrofit = retrofitHelper.getRetrofit(ApiKobisService.BASE_URL);
        
        // Service 객체를 생성한다. 구현체는 Retrofit이 자동으로 생성해 준다.
        ApiKobisService apiKobisService = retrofit.create(ApiKobisService.class);

        /** 2) 검색 파라미터 처리 */
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);

        String date = String.format("%04d-%02d-%02d", 
                c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH));

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
        
        // 검색 결과가 있다면 list만 추출하고 조회결과가 없다면 처리 종료
        if (searchDailyBoxOfficeList != null) {
            list = searchDailyBoxOfficeList.getBoxOfficeResult().getDailyBoxOfficeList();    
        } else {
            log.debug("조회결과 없음");
            return;
        }

        // OpenAPI 조회결과의 Beans에 검색 날짜 추가 후 로그를 통해 내역 확인
        for (DailyBoxOfficeList item : list) {
            item.setTargetDt(targetDt);
            log.debug(item.toString());
        }
        
        /** 4) 수집결과 저장하기 */
        try {
            movieRankService.collectMovieRank(list);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
    
}

