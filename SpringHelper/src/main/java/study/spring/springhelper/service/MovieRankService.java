package study.spring.springhelper.service;

import java.util.List;

import study.spring.springhelper.model.SearchDailyBoxOfficeList.BoxOfficeResult.DailyBoxOfficeList;

public interface MovieRankService {
    /**
     * 영화진흥원 OpenAPI의 수집 결과를 DB에 저장한다.
     * 
     * @param list  영화진흥원 수집 결과
     * @throws Exception
     */
    public void collectMovieRank(List<DailyBoxOfficeList> list) throws Exception;
}

