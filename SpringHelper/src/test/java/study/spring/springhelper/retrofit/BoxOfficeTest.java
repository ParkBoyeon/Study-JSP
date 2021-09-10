package study.spring.springhelper.retrofit;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Retrofit;
import study.spring.springhelper.helper.RetrofitHelper;
import study.spring.springhelper.model.SearchDailyBoxOfficeList;
import study.spring.springhelper.model.SearchDailyBoxOfficeList.BoxOfficeResult.DailyBoxOfficeList;
import study.spring.springhelper.service.ApiKobisService;

/** Lombok의 Log4j 객체 */
// import lombok.extern.slf4j.Slf4j;
@Slf4j
/** JUnit에 의한 테스트 클래스로 정의 */
// import org.junit.runner.RunWith;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
/** Spring의 설정 파일들을 읽어들이도록 설정 (**은 `모든` 이라는 의미) */
// import org.springframework.test.context.ContextConfiguration;
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*-context.xml" })
/** 웹 어플리케이션임을 명시 */
// import org.springframework.test.context.web.WebAppConfiguration;
@WebAppConfiguration
/** 메서드 이름순서로 실행하도록 설정 (설정하지 않을 경우 무작위 순서로 실행됨) */
// import org.junit.FixMethodOrder;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoxOfficeTest {

    // RetrofitHelper 객체 주입 설정
    @Autowired
    RetrofitHelper retrofitHelper;

    /** 원격지 JSON을 가져오는 테스트 메서드 */
    @Test
    public void testA() {
        /** 1) API 연동 객체 생성 */
        // Retrofit 객체 생성
        Retrofit retrofit = retrofitHelper.getRetrofit(ApiKobisService.BASE_URL);

        // Service 객체를 생성한다. 구현체는 Retrofit이 자동으로 생성해 준다.
        ApiKobisService apiKobisService = retrofit.create(ApiKobisService.class);

        /** 2) 검색 파라미터 처리 */
        // 영화진흥원은 yyyymmdd 형식의 문자열을 검색어로 받는다.
        String targetDt = "20200901";

        // 영화진흥원 OpenAPI를 통해 검색결과 받아오기
        Call<SearchDailyBoxOfficeList> call = apiKobisService.getSearchDailyBoxOfficeList(targetDt);
        SearchDailyBoxOfficeList searchDailyBoxOfficeList = null;
        try {
            searchDailyBoxOfficeList = call.execute().body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /** 3) 조회결과를 반복문을 활용해서 출력한다. */
        if (searchDailyBoxOfficeList != null) {
            List<DailyBoxOfficeList> list = searchDailyBoxOfficeList.getBoxOfficeResult().getDailyBoxOfficeList();
            
            for (DailyBoxOfficeList item : list ) {
                log.debug(" >>> " + item.toString());
            }
        }
    }
}