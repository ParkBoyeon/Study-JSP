package study.helloweb.model;

import lombok.Data;

/**
 * 학과정보를 저장하기 위한 클래스
 */
@Data
public class Department {
    /** 학과 번호 */
    private int deptno;

    /** 학과 이름 */
    private String dname;

    /** 학과 위치 */
    private String loc;

}
