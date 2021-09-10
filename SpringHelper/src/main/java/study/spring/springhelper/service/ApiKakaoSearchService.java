package study.spring.springhelper.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import study.spring.springhelper.model.Image;

public interface ApiKakaoSearchService {
	public static final String BASE_URL = "https://dapi.kakao.com";
	
	/**
	 * curl -v -X GET "https://dapi.kakao.com/v2/search/image" \
	 * --data-urlencode "query=설현" \
	 * -H "Authorization: KakaoAK caf7b3b4431b55d7620931b8ee0a7e92"
	 */
	@Headers("Authorization: KakaoAK caf7b3b4431b55d7620931b8ee0a7e92")
	@GET("/v2/search/image")
	Call<Image> getImage(@Query("query") String query, @Query("page") int page, @Query("size") int size);

}

