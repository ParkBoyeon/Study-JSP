package study.spring.springhelper.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;
import study.spring.springhelper.model.Professor;

/** Lombok의 Log4j 객체 */
//import lombok.extern.slf4j.Slf4j;
@Slf4j
/** JUnit에 의한 테스트 클래스로 정의 */
//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
/** Spring의 설정 파일들을 읽어들이도록 설정 (**은 `모든` 이라는 의미) */
//import org.springframework.test.context.ContextConfiguration;
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*-context.xml" })
/** 웹 어플리케이션임을 명시 */
//import org.springframework.test.context.web.WebAppConfiguration;
@WebAppConfiguration
/** 메서드 이름순서로 실행하도록 설정 (설정하지 않을 경우 무작위 순서로 실행됨) */
//import org.junit.FixMethodOrder;
public class ProfessorServiceTest {
	
	// Service 객체 주입
	@Autowired private ProfessorService professorService;

	
	// 단일행 조회
	@Test
	public void testA() {
		Professor input = new Professor();
		input.setProfno(2);
		Professor output = null;
		try {
			output = professorService.getProfessorItem(input);
			log.debug(output.toString());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	// 다중행 조회
	@Test
	public void testB() {
		Professor input = new Professor();
		List<Professor> output = null;
		try {
			output = professorService.getProfessorList(input);
			log.debug(output.toString());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	// 전체 데이터 수 조회
	@Test
	public void testC() {
		int count = 0;
		try {
			count = professorService.getProfessorCount(null);
			log.debug("전체 데이터 수 : " + count);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	// 조건에 따른 데이터 수
	@Test
	public void testD() {
		int count = 0;
		Professor input = new Professor();
		input.setName("박보연");
		try {
			count = professorService.getProfessorCount(input);
			log.debug("검색 데이터 수 : " + count);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	// 데이터 저장
	@Test
	public void testE() {
		Professor input = new Professor();
		input.setName("박보연");
		input.setUserid("boyeon");
		input.setHiredate("2021-03-03");
		input.setSal(500);
		input.setComm(10);
		input.setDeptno(101);
		input.setPosition("교수");
		int output = 0;
		try {
			output = professorService.addProfessor(input);
			log.debug("저장된 데이터 수 : " + output);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	// 데이터 수정 
	@Test
	public void testF() {
		Professor input = new Professor();
		input.setName("박보연");
		input.setUserid("boyeon");
		input.setHiredate("2021-03-03");
		input.setSal(500);
		input.setComm(10);
		input.setDeptno(101);
		input.setPosition("교수");
		int output = 0;
		try {
			output = professorService.editProfessor(input);
			log.debug("수정된 데이터 수 : " + output);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	// 데이터 삭제
	@Test
	public void testG() {
		Professor input = new Professor();
		input.setDeptno(9901);
		int output = 0;
		try {
			output = professorService.deleteProfessor(input);
			log.debug("삭제된 데이터 수 : " + output);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}
