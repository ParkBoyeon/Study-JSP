package study.spring.simplespring.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import study.spring.simplespring.helper.PageData;
import study.spring.simplespring.model.Department;
import study.spring.simplespring.service.DepartmentService;

/** 학과 관리 컨트롤러 */
@Controller
public class DepartmentController {

	/** Service 패턴 구현체 주입 */
	// --> import study.spring.springhelper.service.DepartmentService;
	@Autowired
	DepartmentService departmentService;

	/** "/프로젝트이름" 에 해당하는 ContextPath 변수 주입 */
	// --> import org.springframework.beans.factory.annotation.Value;
	@Value("#{servletContext.contextPath}")
	String contextPath;

	/** 목록 페이지 */
	@RequestMapping(value = "/department/list.do", method = RequestMethod.GET)
	public String list(Model model, HttpServletResponse response,
			// 검색어
			@RequestParam(value = "keyword", required = false) String keyword,
			// 페이지 구현에서 사용할 현재 페이지 번호
			@RequestParam(value = "page", defaultValue = "1") int nowPage) {

		int totalCount = 0; // 전체 게시글 수
		int listCount = 10; // 한 페이지당 표시할 목록 수
		int pageCount = 5; // 한 그룹당 표시할 페이지 번호 수

		// 조회에 필요한 조건값(검색어)를 Beans에 담는다.
		Department input = new Department();
		input.setDname(keyword);
		input.setLoc(keyword);

		List<Department> output = null; // 조회결과가 저장될 객체
		PageData pageData = null; // 페이지 번호를 계산한 결과가 저장될 객체

		try {
			// 전체 게시글 수 조회
			totalCount = departmentService.getDepartmentCount(input);
			// 페이지 번호 계산 --> 계산결과를 로그로 출력될 것이다.
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);

			// SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
			Department.setOffset(pageData.getOffset());
			Department.setListCount(pageData.getListCount());

			// 데이터 조회하기
			output = departmentService.getDepartmentList(input);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// View 처리
		model.addAttribute("output", output);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pageData", pageData);
		return "department/list";
	}

	/** 작성 폼 페이지 */
	@RequestMapping(value = "/department/add.do", method = RequestMethod.GET)
	public String add(Model model, HttpServletResponse response) {
		return "department/add";
	}

	/** 작성 폼에 대한 action 페이지 */
	@RequestMapping(value = "/department/add_ok.do", method = RequestMethod.POST)
	public void addOk(Model model, HttpServletResponse response, @RequestParam(value = "dname") String dname,
			@RequestParam(value = "loc") String loc) {

		// 저장할 값들을 Beans에 담는다.
		Department input = new Department();
		input.setDname(dname);
		input.setLoc(loc);

		try {
			// 데이터 저장 --> 데이터 저장에 성공하면 파라미터로 전달하는 input 객체에 PK값이 저장된다.
			departmentService.addDepartment(input);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 저장 결과를 확인하기 위해서 데이터 저장시 생성된 PK값을 상세 페이지로 전달해야 한다.
		String redirectUrl = contextPath + "/department/detail.do?deptno=" + input.getDeptno();

		try {
			response.sendRedirect(redirectUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** 상세 페이지 */
	@RequestMapping(value = "/department/detail.do", method = RequestMethod.GET)
	public String detail(Model model, HttpServletResponse response, @RequestParam(value = "deptno") int deptno) {

		// 데이터 조회에 필요한 조건값을 Beans에 저장하기
		Department input = new Department();
		input.setDeptno(deptno);

		// 조회결과를 저장할 객체 선언
		Department output = null;

		try {
			// 데이터 조회
			output = departmentService.getDepartmentItem(input);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// View 처리
		model.addAttribute("output", output);
		return "department/detail";
	}

	/** 삭제 처리 구현 */
	@RequestMapping(value = "/department/delete_ok.do", method = RequestMethod.GET)
	public void deleteOk(Model model, HttpServletResponse response, @RequestParam(value = "deptno") int deptno) {
		// 데이터 삭제에 필요한 조건값을 Beans에 저장하기
		Department input = new Department();
		input.setDeptno(deptno);

		try {
			// 데이터 삭제
			departmentService.deleteDepartment(input);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 확인할 대상이 삭제된 상태이므로 목록 페이지로 이동
		try {
			response.sendRedirect(contextPath + "/department/list.do");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 수정 폼 페이지 */
	@RequestMapping(value = "/department/edit.do", method = RequestMethod.GET)
	public String edit(Model model, HttpServletResponse response, @RequestParam(value = "deptno") int deptno) {

		// 데이터 조회에 필요한 조건값을 Beans에 저장하기
		Department input = new Department();
		input.setDeptno(deptno);

		// 수정할 데이터의 원본 조회하기
		Department output = null;

		try {
			output = departmentService.getDepartmentItem(input);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// View 처리
		model.addAttribute("output", output);
		return "department/edit";
	}

	/** 수정 폼에 대한 action 페이지 */
	@RequestMapping(value = "/department/edit_ok.do", method = RequestMethod.POST)
	public void editOk(Model model, HttpServletResponse response, @RequestParam(value = "deptno") int deptno,
			@RequestParam(value = "dname") String dname, @RequestParam(value = "loc") String loc) {

		// 수정할 값들을 Beans에 담는다.
		Department input = new Department();
		input.setDeptno(deptno);
		input.setDname(dname);
		input.setLoc(loc);

		try {
			// 데이터 수정
			departmentService.editDepartment(input);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 수정한 대상을 상세페이지에 알려주기 위해서 PK값을 전달해야 한다.
		String redirectUrl = contextPath + "/department/detail.do?deptno=" + input.getDeptno();
		try {
			response.sendRedirect(redirectUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
