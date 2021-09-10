package study.spring.simplespring.model;

import lombok.Data;

/** Join문 구조에 맞춘 Java Beans 생성*/
@Data
public class ProfessorDepartment {
	private int profno;
	private String name;
	private String userid;
	private String position;
	private int sal;
	private String hiredate;
	private int comm;
	private int deptno;
	private String dname;
	private String loc;

}
