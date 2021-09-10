package study.spring.springhelper.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import study.spring.springhelper.model.SearchDailyBoxOfficeList;

public interface ApiKobisService {
	public static final String BASE_URL = "http://www.kobis.or.kr";
	
	@GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=fcb16eefddc89040fea5e8c386992581")
	Call<SearchDailyBoxOfficeList> getSearchDailyBoxOfficeList(@Query("targetDt") String targetDt);
	

}
