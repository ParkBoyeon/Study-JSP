package study.spring.springhelper.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class GradeList {
	@SerializedName("student")		private List<Student> student;
	
	@Data
	public class Student {
		@SerializedName("name")			private String name;
		@SerializedName("kor")			private String kor;
		@SerializedName("eng")			private String eng;
		@SerializedName("math")			private String math;
	
	}

}
