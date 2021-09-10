package study.spring.springhelper.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import study.spring.springhelper.helper.PageData;
import study.spring.springhelper.helper.RegexHelper;
import study.spring.springhelper.helper.WebHelper;
import study.spring.springhelper.model.Department;
import study.spring.springhelper.model.Professor;
import study.spring.springhelper.service.DepartmentService;
import study.spring.springhelper.service.ProfessorService;

@Controller
public class ProfessorController {
	
	/** WebHelper 주입 */
	@Autowired WebHelper webHelper;
	
	/** RegexHelper 주입 */
	@Autowired RegexHelper regexHelper;
	
	/** Service 패턴 구현체 주입 */
	@Autowired ProfessorService professorService;
	
	@Autowired DepartmentService departmentService;
	
	/** "/프로젝트이름" 에 해당하는 ContextPath 변수 주입 */
	@Value("#{servletContext.contextPath}")
	String contextPath;
	
	
	
	/** 목록 페이지 */
	@RequestMapping(value = "/professor/list.do", method = RequestMethod.GET)
	public ModelAndView list(Model model,
			// 검색어
			@RequestParam(value="keyword", required=false) String keyword,
			// 페이지 구현에서 사용할 현재 페이지 번호
			@RequestParam(value="page", defaultValue="1") int nowPage) {
		
		/** 1) 페이지 구현에 필요한 변수값 생성 */
		int totalCount = 0;		// 전체 게시글 수
		int listCount = 10;		// 한 페이지당 표시할 목록 수
		int pageCount = 5;		// 한 그룹당 표시할 페이지 번호 수
		
		/** 2) 데이터 조회하기 */
		// 조회에 필요한 조건값(검색어)를 Beans에 담는다.
		Professor input = new Professor();
		input.setName(keyword);
		
		List<Professor> output = null;		// 조회결과가 저장될 객체
		PageData pageData = null;
		
		try {
			// 전체 게시글 수 조회
			totalCount = professorService.getProfessorCount(input);
			// 페이지 번호 계산 --> 계산 결과를 로그로 출력될 것이다.
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);
			
			// SQL의 LIMIT 절에서 사용될 값을 Beans의 static 변수에 저장
			Professor.setOffset(pageData.getOffset());
			Professor.setListCount(pageData.getListCount());
			
			// 데이터 조회하기
			output = professorService.getProfessorList(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		
		
		/** 3) View 처리 */
		model.addAttribute("keyword", keyword);
		model.addAttribute("output", output);
		model.addAttribute("pageData", pageData);
		
		return new ModelAndView("professor/list"); 
	}
	
	 /** 상세 페이지 */
    @RequestMapping(value = "/professor/view.do", method = RequestMethod.GET)
    public ModelAndView view(Model model,
            @RequestParam(value="profno", defaultValue="0") int profno) {
        
        /** 1) 유효성 검사 */
        // 이 값이 존재하지 않는다면 데이터 조회가 불가능하므로 반드시 필수값으로 처리해야 한다.
        if (profno == 0) {
            return webHelper.redirect(null, "교수번호가 없습니다.");
        }

        /** 2) 데이터 조회하기 */
        // 데이터 조회에 필요한 조건값을 Beans에 저장하기
        Professor input = new Professor();
        input.setProfno(profno);

        // 조회결과를 저장할 객체 선언
        Professor output = null;

        try {
            // 데이터 조회
            output = professorService.getProfessorItem(input);
        } catch (Exception e) {
            return webHelper.redirect(null, e.getLocalizedMessage());
        }
        
        /** 3) View 처리 */
        model.addAttribute("output", output);
        return new ModelAndView("professor/view");
    }
	
	/** 작성 폼 페이지 */
	@RequestMapping(value = "/professor/add.do", method = RequestMethod.GET)
	public ModelAndView add(Model model) { 
		/** 학과 목록 조회하기 */
		// 조회결과를 저장할 객체 선언
		List<Department> output = null;
		
		try {
			// 데이터 조회 --> 검색 조건 없이 모든 학과 조회
			output = departmentService.getDepartmentList(null);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		
		// View에 추가
		model.addAttribute("output", output);
		
		return new ModelAndView("professor/add"); 
	}
	
	/** 작성 폼에 대한 action 페이지 */
	@RequestMapping(value = "/professor/add_ok.do", method = RequestMethod.POST)
	public ModelAndView add_ok(Model model,
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="userid", defaultValue="") String userid,
			@RequestParam(value="position", defaultValue="") String position,
			@RequestParam(value="sal", defaultValue="") int sal,
			@RequestParam(value="hiredate", defaultValue="") String hiredate,
			@RequestParam(value="comm", defaultValue="") Integer comm,
			@RequestParam(value="deptno", defaultValue="") int deptno) { 
		
		/** 1) 사용자가 입력한 파라미터 유효성 검사 */
		// 일반 문자열 입력 컬럼 --> String으로 파라미터가 선언되어 있는 경우는 값이 입력되지 않으면 빈 문자열로 처리된다.
		if (!regexHelper.isValue(name)) { return webHelper.redirect(null, "교수 이름을 입력하세요"); }
		if (!regexHelper.isKor(name)) { return webHelper.redirect(null, "교수 이름은 한글만 가능합니다."); }
		if (!regexHelper.isValue(userid)) { return webHelper.redirect(null, "교수 아이디를 입력하세요."); }
		if (!regexHelper.isEngNum(userid)) { return webHelper.redirect(null, "교수 아이디는 영어와 숫자로만 가능합니다."); }
		if (!regexHelper.isValue(position)) { return webHelper.redirect(null, "직급을 입력하세요."); }
		if (!regexHelper.isValue(hiredate)) { return webHelper.redirect(null, "입사일을 입력하세요."); }
		
		// 숫자형으로 선언된 파라미터()
		if (sal == 0) { return webHelper.redirect(null, "급여를 입력하세요."); }
		if (sal < 0) { return webHelper.redirect(null, "급여는 0보다 작을 수 없습니다."); }
		if (comm < 0) { return webHelper.redirect(null, "보직수당은 0보다 작을 수 없습니다."); }
		if (deptno == 0) { return webHelper.redirect(null, "소속 학과 번호를 입력하세요."); }
		
		/** 2) 데이터 저장하기 */
		// 저장할 값들을 Beans에 담는다.
		Professor input = new Professor();
		input.setName(name);
		input.setUserid(userid);
		input.setPosition(position);
		input.setSal(sal);
		input.setHiredate(hiredate);
		input.setComm(comm);
		input.setDeptno(deptno);
		
		try {
			// 데이터 저장
			// --> 데이터 저장에 성공하면 파라미터로 전달하는 input 객체에 PK값이 저장된다.
			professorService.addProfessor(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		
		/** 3) 결과를 확인하기 위한 페이지 이동 */
		// 저장 결과를 확인하기 위해서 데이터 저장시 생성된 PK값을 상세 페이지로 전달해야 한다. 
		String redirectUrl = contextPath + "/professor/view.do?profno=" + input.getProfno();
		return webHelper.redirect(redirectUrl, "저장되었습니다."); 
	}
	
	/** 수정 폼 페이지 */
    @RequestMapping(value = "/professor/edit.do", method = RequestMethod.GET)
    public ModelAndView edit(Model model,
            @RequestParam(value="profno", defaultValue="0") int profno) {
        
        /** 1) 파라미터 유효성 검사 */
        // 이 값이 존재하지 않는다면 데이터 조회가 불가능하므로 반드시 필수값으로 처리해야 한다.
        if (profno == 0) {
            return webHelper.redirect(null, "교수번호가 없습니다.");
        }
        
        /** 2) 데이터 조회하기 */
        // 데이터 조회에 필요한 조건값을 Beans에 저장하기
        Professor input = new Professor();
        input.setProfno(profno);
        
        // 교수 조회결과를 저장할 객체 선언
        Professor output = null;

        // 학과목록을 선택할 수 있는 드롭다운을 위한 조회결과를 저장할 객체 선언
        List<Department> deptList = null;
        
        try {
            // 교수 기본 정보 조회
            output = professorService.getProfessorItem(input);
            // 드롭다운을 위한 학과목록 조회
            deptList = departmentService.getDepartmentList(null);
        } catch (Exception e) {
            return webHelper.redirect(null, e.getLocalizedMessage());
        }
        
        /** 3) View 처리 */
        model.addAttribute("output", output);
        model.addAttribute("deptList", deptList);
        return new ModelAndView("professor/edit");
    }
	
	/** 수정 폼에 대한 action 페이지 */
	@RequestMapping(value = "/professor/edit_ok.do", method = RequestMethod.POST)
	public ModelAndView edit_ok(Model model,
			@RequestParam(value="profno", defaultValue="0") int profno,
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="userid", defaultValue="") String userid,
			@RequestParam(value="position", defaultValue="") String position,
			@RequestParam(value="sal", defaultValue="0") int sal,
			@RequestParam(value="hiredate", defaultValue="") String hiredate,
			@RequestParam(value="comm", defaultValue="0") Integer comm,
			@RequestParam(value="deptno", defaultValue="0") int deptno) { 
		
		/** 1) 사용자가 입력한 파라미터 유효성 검사 */
		// 상태유지 처리된 PK값에 대한 검사
		if (profno == 0) { return webHelper.redirect(null, "교수번호가 없습니다."); }
		
		// 일반 문자열 입력 컬럼 --> String으로 파라미터가 선언되어 있는 경우는 값이 입력되지 않으면 빈 문자열로 처리된다.
		if (!regexHelper.isValue(name)) { return webHelper.redirect(null, "교수 이름을 입력하세요"); }
		if (!regexHelper.isKor(name)) { return webHelper.redirect(null, "교수 이름은 한글만 가능합니다."); }
		if (!regexHelper.isValue(userid)) { return webHelper.redirect(null, "교수 아이디를 입력하세요."); }
		if (!regexHelper.isEngNum(userid)) { return webHelper.redirect(null, "교수 아이디는 영어와 숫자로만 가능합니다."); }
		if (!regexHelper.isValue(position)) { return webHelper.redirect(null, "직급을 입력하세요."); }
		if (!regexHelper.isValue(hiredate)) { return webHelper.redirect(null, "입사일을 입력하세요."); }
				
		// 숫자형으로 선언된 파라미터()
		if (sal == 0) { return webHelper.redirect(null, "급여를 입력하세요."); }
		if (sal < 0) { return webHelper.redirect(null, "급여는 0보다 작을 수 없습니다."); }
		if (comm < 0) { return webHelper.redirect(null, "보직수당은 0보다 작을 수 없습니다."); }
		if (deptno == 0) { return webHelper.redirect(null, "소속 학과 번호를 입력하세요."); }
		
		/** 2) 데이터 수정하기 */
		// 수정할 값들을 Beans에 담는다.
		Professor input = new Professor();
		input.setProfno(profno);
		input.setName(name);
		input.setUserid(userid);
		input.setPosition(position);
		input.setSal(sal);
		input.setHiredate(hiredate);
		input.setComm(comm);
		input.setDeptno(deptno);
		
		try {
			// 데이터 수정
			professorService.addProfessor(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		
		/** 3) 결과를 확인하기 위한 페이지 이동 */
		// 수정한 대상을 상세페이지에 알려주기 위해서 PK값을 전달해야 한다.
		String redirectUrl = contextPath + "/professor/view.do?profno=" + input.getProfno();
		return webHelper.redirect(redirectUrl, "수정되었습니다."); 
	}
	
	/** 삭제 처리 */
	@RequestMapping(value = "/professor/delete_ok.do", method = RequestMethod.GET)
	public ModelAndView delete_ok(Model model,
			@RequestParam(value="profno", defaultValue="0") int profno) { 
		
		/** 1) 파라미터 유효성 검사 */
		// 이 값이 존재하지 않는다면 데이터 삭제가 불가능하므로 반드시 필수값으로 처리해야 한다. 
		if (profno == 0) {
			return webHelper.redirect(null, "교수 번호가 없습니다.");
		}
		
		/** 2) 데이터 삭제하기 */
		// 데이터 삭제에 필요한 조건값을 Beans에 저장하기 
		Professor input = new Professor();
		input.setProfno(profno);
		
		try {
			professorService.deleteProfessor(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		
		/** 3) 페이지 이동 */
		// 확인할 대상이 삭제된 상태이므로 목록 페이지로 이동
		return webHelper.redirect(contextPath + "/professor/list.do", "삭제되었습니다."); 
	}

}
