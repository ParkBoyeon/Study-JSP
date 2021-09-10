package study.spring.simplespring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import study.spring.simplespring.service.ProfessorService;

/** 컨트롤러 지정 */
@Controller
public class ProfessorController {
	/** 객체 주입 --> 자동할당 */
	@Autowired
	private ProfessorService professorService;
	
	/** 이 메서드를 "/service/professor.do" URL에 GET 방식으로 노출시킨다. */
	@RequestMapping(value="/service/professor.do", method=RequestMethod.GET)
	public String professor(Model model) {
		model.addAttribute("add", professorService.addData());
		model.addAttribute("getItem", professorService.getDataItem());
		model.addAttribute("getList", professorService.getDataList());
		model.addAttribute("edit", professorService.editData());
		model.addAttribute("delete", professorService.deleteData());
		
		// "/src/main/webapp/WEB-INF/views/service/professor.jsp" 파일을 View로 지정한다.
		return "service/professor";
	}

}
