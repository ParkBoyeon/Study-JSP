package study.jsp.model1.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class HelloModel {
	private int id;
	private String name;
	private String email;
	
	public void printLog() {
		log.debug("HelloModel 객체가 생성되었습니다.");
	}
}
