package study.spring.simplespring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//이 클래스를 컨트롤러로서 지정함
// -> import org.springframework.stereotype.Controller;
@Controller
public class ParamsController {
    /** 첫 페이지의 역할을 하는 메서드 */
    // -> import org.springframework.web.bind.annotation.RequestMapping;
    // -> import org.springframework.web.bind.annotation.RequestMethod;
    @RequestMapping(value="/params/home.do", method=RequestMethod.GET)
    public String home() {
        // "/src/main/webapp/WEB-INF/views/params/home.jsp" 파일을 View로 지정한다.
        return "params/home";
    }
    
    /** GET 방식의 파라미터를 전송받기 위한 컨트롤러 메서드 */
    // 변수값을 View에게 전달할 필요가 있는 경우 Model 파라미터를 정의한다.
    // import org.springframework.ui.Model;
    @RequestMapping(value = "/params/get.do", method = RequestMethod.GET)
    public String get(Model model, 
            // GET,POST 파라미터 받기
            @RequestParam(value="answer", defaultValue="0") int ans) {        
        String result = null;
        
        if (ans == 300) {
            result = "정답입니다.";
        } else {
            result = "오답입니다.";
        }
        
        // 파라미터값을 View에게 전달한다.
        model.addAttribute("ans", ans);
        model.addAttribute("result", result);
        
        // "/src/main/webapp/WEB-INF/views/params/get.jsp" 파일을 View로 지정한다.
        return "params/get";
    }

    /** POST 방식의 파라미터를 전송받기 위한 컨트롤러 메서드 */
    // 추가적인 파라미터가 필요하다면 콤마(,)로 구분하여 나열한다.
    @RequestMapping(value = "/params/post.do", method = RequestMethod.POST)
    public String post(Model model, 
            @RequestParam(value="user_name", defaultValue="") String name,
            @RequestParam(value="user_age", defaultValue="0") int age) {        
        // 파라미터값을 View에게 전달한다.
        model.addAttribute("name", name);
        model.addAttribute("age", age);

        // "/src/main/webapp/WEB-INF/views/params/post.jsp" 파일을 View로 지정한다.
        return "params/post";
    }
    
    /** PATH 파라미터를 전송받기 위한 컨트롤러 메서드 (1) */
    // import org.springframework.web.bind.annotation.PathVariable;
    @RequestMapping(value = "/params/path1/{txt1}/{txt2}/{num}", method = RequestMethod.GET)
    public String path1(Model model, 
            @PathVariable String txt1, 
            @PathVariable String txt2, 
            @PathVariable int num) {
        
        // 파라미터값을 View에게 전달한다.
        model.addAttribute("txt1", txt1);
        model.addAttribute("txt2", txt2);
        model.addAttribute("num", num);

        // "/src/main/webapp/WEB-INF/views/params/path1.jsp" 파일을 View로 지정한다.
        return "params/path1";
    }
    
    /** PATH 파라미터를 전송받기 위한 컨트롤러 메서드 (2) */
    @RequestMapping(value = "/params/path2/{txt1}/{txt2}/{num}", method = RequestMethod.GET)
    public String path2(Model model, 
            @PathVariable(value="txt1") String textValue1, 
            @PathVariable(value="txt2") String textValue2, 
            @PathVariable(value="num") int numberValue) {
        
        // 파라미터값을 View에게 전달한다.
        model.addAttribute("textValue1", textValue1);
        model.addAttribute("textValue2", textValue2);
        model.addAttribute("numberValue", numberValue);

        // "/src/main/webapp/WEB-INF/views/params/path2.jsp" 파일을 View로 지정한다.
        return "params/path2";
    }
}






