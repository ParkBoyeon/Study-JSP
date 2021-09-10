package study.spring.simplespring.service;

import java.util.List;

import study.spring.simplespring.model.Department;

/** 학과 데이터 관리 기능을 제공하기 위한 Service 계층. */
public interface DepartmentService {
    /**
     * 학과 데이터 상세 조회
     * @param Department 조회할 학과의 일련번호를 담고 있는 Beans
     * @return 조회된 데이터가 저장된 Beans
     * @throws Exception
     */
    public Department getDepartmentItem(Department input) throws Exception;

    /**
     * 학과 데이터 목록 조회
     * @return 조회 결과에 대한 컬렉션
     * @throws Exception
     */
    public List<Department> getDepartmentList(Department input) throws Exception;

    /**
     * 학과 데이터가 저장되어 있는 갯수 조회
     * @return int
     * @throws Exception
     */
    public int getDepartmentCount(Department input) throws Exception;

    /**
     * 학과 데이터 등록하기
     * @param Department 저장할 정보를 담고 있는 Beans
     * @return int
     * @throws Exception
     */
    public int addDepartment(Department input) throws Exception;
    
    /**
     * 학과 데이터 수정하기
     * @param Department 수정할 정보를 담고 있는 Beans
     * @return int
     * @throws Exception
     */
    public int editDepartment(Department input) throws Exception;
    
    /**
     * 학과 데이터 삭제하기
     * @param Department 삭제할 학과의 일련번호를 담고 있는 Beans
     * @return int
     * @throws Exception
     */
    public int deleteDepartment(Department input) throws Exception;
}

