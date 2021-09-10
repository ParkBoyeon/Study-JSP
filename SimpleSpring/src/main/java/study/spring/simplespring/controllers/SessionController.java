package study.spring.simplespring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SessionController {
    /**
     * 세션 저장을 위한 작성 페이지 
     * --> 이 페이지를 "/session/write.do" URL에 GET 방식으로 노출시킨다.
     */
    @RequestMapping(value = "/session/write.do", method = RequestMethod.GET)
    public String sessionWrite(Model model, HttpServletRequest request) {        
        /** 컨트롤러에서 세션을 식별하기 위한 처리 */
        // 세션값은 request 내장객체를 통해서 HttpSession객체를 생성해야 접근할 수 있다.
        // --> Servlet과 동일함.
        HttpSession session = request.getSession();
        String mySession = (String) session.getAttribute("my_session_value");
        if (mySession == null) {
            mySession = "";
        }
        
        // 추출한 값을 View에게 전달 
        model.addAttribute("my_session", mySession);

        // "/src/main/webapp/WEB-INF/views/session/write.jsp" 파일을 View로 지정한다.
        return "session/write";
    }

    /**
     * 세션을 저장하기 위한 action 페이지 
     * --> 이 페이지를 "/session/save.do" URL에 POST 방식으로 노출시킨다.
     */
    @RequestMapping(value = "/session/save.do", method = RequestMethod.POST)
    public String sessionSave(HttpServletRequest request, 
            @RequestParam(value="user_input", defaultValue="") String userInput) {
        /** 1) request 객체를 사용해서 세션 객체 만들기 */
        // --> import javax.servlet.http.HttpSession;
        HttpSession session = request.getSession();

        /** 2) 세션 저장, 삭제 */
        // 생성된 세션 객체를 사용하는 방법은 JSP와 동일하다.
        if (!userInput.equals("")) {
            // 입력 내용이 있다면 세션 저장 처리
            session.setAttribute("my_session_value", userInput);
        } else {
            // 입력 내용이 없는 경우 세션 삭제
            session.removeAttribute("my_session_value");
        }

        /** 3) Spring방식의 페이지 이동. */
        // Servlet의 response.sendRedirect(url)과 동일
        // --> "/"부터 시작할 경우 ContextPath는 자동으로 앞에 추가된다.
        return "redirect:/session/write.do";
    }
}
