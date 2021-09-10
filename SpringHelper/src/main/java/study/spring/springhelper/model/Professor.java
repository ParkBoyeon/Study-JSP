package study.spring.springhelper.model;

import lombok.Data;

@Data
public class Professor {
	// 1) 기본 컬럼
	/** 교수번호 (PK) */
	private int profno;
	/** 교수이름 */
	private String name;
	/** 아이디 */
	private String userid;
	/** 직급 */
	private String position;
	/** 급여 */
	private int sal;
	/** 입사일 */
	private String hiredate;
	/** 보직수당 */
	private Integer comm; 
	/** 소속학과 번호 */
	private int deptno;
	
	// 2) JOIN절에 따른 추가 컬럼
	/** 소속학과 이름 (department 테이블과의 join) */
	private String dname;
	/** 소속학과 위치 (department 테이블과의 join) */
	private String loc;
	
	// 3) 페이지 구현을 위한 static 변수
	/** LIMIT 절에서 사용할 조회 시작 위치 */
	private static int offset;
	
	/** LIMIT 절에서 사용할 조회할 데이터 수 */
	private static int listCount;

	public static int getOffset() {
		return offset;
	}

	public static void setOffset(int offset) {
		Professor.offset = offset;
	}

	public static int getListCount() {
		return listCount;
	}

	public static void setListCount(int listCount) {
		Professor.listCount = listCount;
	}
	
	
}
