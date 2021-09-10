package study.spring.simplespring.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;
import study.spring.simplespring.model.Department;
import study.spring.simplespring.model.ProfessorDepartment;

/** Lombok의 Log4j 객체 */
// import lombok.extern.slf4j.Slf4j;
@Slf4j
/** JUnit에 의한 테스트 클래스로 정의 */
// import org.junit.runner.RunWith;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
/** Spring의 설정 파일들을 읽어들이도록 설정 (**은 `모든` 이라는 의미) */
// import org.springframework.test.context.ContextConfiguration;
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*-context.xml" })
/** 웹 어플리케이션임을 명시 */
// import org.springframework.test.context.web.WebAppConfiguration;
@WebAppConfiguration
/** 메서드 이름순서로 실행하도록 설정 (설정하지 않을 경우 무작위 순서로 실행됨) */
// import org.junit.FixMethodOrder;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentTest {

    /** MyBatis의 SQL세션 주입 설정 */
    // import org.springframework.beans.factory.annotation.Autowired;
    @Autowired
    private SqlSession sqlSession;

    /** 단일행 조회 테스트 */
    @Test
    public void testA() {    
        // import study.spring.simplespring.model.Department;
        Department input = new Department();
        input.setDeptno(101);
        sqlSession.selectOne("DepartmentMapper.selectItem", input);
    }
    
    /** 다중행 조회 테스트 */
    @Test
    public void testB() {    
        // import study.spring.simplespring.model.Department;
        Department input = new Department();
        input.setDname("공학");
        sqlSession.selectList("DepartmentMapper.selectList", input);
    }

    /** 데이터 저장 테스트 */
    @Test
    public void testC() {        
        Department input = new Department();
        input.setDname("신규학과");
        sqlSession.insert("DepartmentMapper.insertItem", input);
    }
    
    /** 데이터 삭제 테스트 */
    @Test
    public void testD() {        
        Department input = new Department();
        input.setDeptno(203);
        sqlSession.delete("DepartmentMapper.deleteItem", input);
    }

    /** 데이터 수정 테스트 */
    @Test
    public void testE() {        
        Department input = new Department();
        input.setDeptno(204);
        input.setDname("수정학과");
        input.setLoc("5호관");
        sqlSession.update("DepartmentMapper.updateItem", input);
    }

    /** 전체 데이터 수 조회 */
    @Test
    public void testF() {
        int count = sqlSession.selectOne("DepartmentMapper.selectCountAll", null);
        log.debug("전체 데이터 수: " + count);
    }

    /** 조건에 따른 데이터 수 조회 */
    @Test
    public void testG() {    
        // import study.spring.simplespring.model.Department;
        Department input = new Department();
        input.setDname("공학");
        int count = sqlSession.selectOne("DepartmentMapper.selectCountAll", input);
        log.debug("공학을 포함하는 학과이름을 갖는 데이터 수: " + count);
    }

    /** Join을 활용한 데이터 수 조회 */
    @Test
    public void testH() {    
        // import study.spring.simplespring.model.ProfessorDepartment;
        ProfessorDepartment input = new ProfessorDepartment();
        input.setDname("공학");
        sqlSession.selectList("ProfessorDepartmentMapper.selectJoin", input);
    }
}