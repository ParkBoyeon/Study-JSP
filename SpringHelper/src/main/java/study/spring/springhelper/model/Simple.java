package study.spring.springhelper.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * JSON 데이터를 표현하는 Beans 클래스
 * "@SerializedName("json의 key")가 명시된 변수에 JSON의 값을 저장한다.
 *
 */
@Data
public class Simple {
	@SerializedName("name") private String name;
	@SerializedName("type") private String type;
	@SerializedName("img") private String img;
	

}
