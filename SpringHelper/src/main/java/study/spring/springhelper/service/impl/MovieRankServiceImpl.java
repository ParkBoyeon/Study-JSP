package study.spring.springhelper.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import study.spring.springhelper.model.SearchDailyBoxOfficeList.BoxOfficeResult.DailyBoxOfficeList;
import study.spring.springhelper.service.MovieRankService;

@Slf4j
@Service
public class MovieRankServiceImpl implements MovieRankService {

    // MyBatis 연동을 위한 객체 주입
    @Autowired SqlSession sqlSession;
    
    @Override
    public void collectMovieRank(List<DailyBoxOfficeList> list) throws Exception {
        try {
            // 수집 결과 수 만큼 반복을 수행하면서 데이터를 저장한다.
            for (DailyBoxOfficeList item : list) {
            	// 검색일과 영화 제목이 일치하는 데이터에 대한 UPDATE를 시도한다.
            	if (sqlSession.update("MovieRankMapper.updateItem", item) == 0) {
                    // 갱신된 결과가 0이라면 조건이 일치하는 데이터가 없다는 의미이므로 INSERT 수행
                    sqlSession.insert("MovieRankMapper.insertItem", item);
            	}
            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 저장에 실패했습니다.");
        }
    }

}
