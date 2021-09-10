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
public class ProfessorAjaxController {
	
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
	@RequestMapping(value = "/professor_ajax/list.do", method = RequestMethod.GET)
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
		
		return new ModelAndView("professor/list_ajax"); 
	}
	
	 /** 상세 페이지 */
    @RequestMapping(value = "/professor_ajax/view.do", method = RequestMethod.GET)
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
        return new ModelAndView("professor/view_ajax");
    }
	
	/** 작성 폼 페이지 */
	@RequestMapping(value = "/professor_ajax/add.do", method = RequestMethod.GET)
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
		
		return new ModelAndView("professor/add_ajax"); 
	}
	
	
	
	/** 수정 폼 페이지 */
    @RequestMapping(value = "/professor_ajax/edit.do", method = RequestMethod.GET)
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
        return new ModelAndView("professor/edit_ajax");
    }
	
	
	
	

}
