package study.spring.springhelper.service;

import retrofit2.Call;
import retrofit2.http.GET;
import study.spring.springhelper.model.GradeList;
import study.spring.springhelper.model.Phone;
import study.spring.springhelper.model.Simple;

public interface ApiSimpleService {
	public static final String BASE_URL = "http://itpaper.co.kr";
	
	@GET("/demo/java/simple.json")
	Call<Simple> getSimple();
	
	@GET("/demo/java/phone.json")
	Call<Phone> getPhone();
	
	@GET("/demo/java/grade_list.json")
	Call<GradeList> getGradeList();
	

}
