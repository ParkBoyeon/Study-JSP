package study.spring.simplespring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import study.spring.simplespring.vo.Calc1;
import study.spring.simplespring.vo.Calc2;
import study.spring.simplespring.vo.Calc3;
import study.spring.simplespring.vo.Calc4;

/** 컨트롤러 지정 */
@Controller
public class AOPController {
	/** 객체 주입 --> 자동 할당 */
	@Autowired
	private Calc1 my_calc;		// id 갑에 의해 Calc1 객체를 할당한다.
	@Autowired
	private Calc2 my_calc2;
	@Autowired
	private Calc3 my_calc3;
	@Autowired
	private Calc4 my_calc4;
	
	/** 이 메서드를 "/aop/calc.do" URL에 GET방식으로 노출시킨다. */
	@RequestMapping(value="/aop/calc.do", method=RequestMethod.GET)
	public String calc(Model model) {
		// Spring에 의해 자동으로 주입된 객체의 메서드를 호출한다.
		int value1 = my_calc.sum();
		int value2 = my_calc2.sum();
		int value3 = my_calc3.sum();
		int value4 = my_calc4.sum();
		
		// 변수값을 View에게 전달한다. 
		model.addAttribute("value1", value1);
		model.addAttribute("value2", value2);
		model.addAttribute("value3", value3);
		model.addAttribute("value4", value4);
		
		// "/src/main/webapp/WEB-INF/views/aop/calc.jsp" 파일을 View로 지정한다. 
		return "aop/calc";
	}

}
