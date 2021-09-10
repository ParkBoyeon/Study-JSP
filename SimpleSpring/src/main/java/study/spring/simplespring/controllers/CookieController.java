package study.spring.simplespring.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CookieController {
    /**
     * 쿠키 저장을 위한 작성 페이지
     * --> 이 페이지를 "/cookie/write.do" URL에 GET 방식으로 노출시킨다.
     */
    @RequestMapping(value = "/cookie/write.do", method = RequestMethod.GET)
    public String cookieWrite(Model model,
            // -> import org.springframework.web.bind.annotation.CookieValue;
            @CookieValue(value="my_cookie", defaultValue="") String myCookie) {        
        /** 컨트롤러에서 쿠키를 식별하기 위한 처리 */
        try {
            // 저장시에 URLEncoding이 적용되었으므로 URLDecoding이 별도로 필요함
            myCookie = URLDecoder.decode(myCookie, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        // 추출한 값을 View에게 전달
        model.addAttribute("my_cookie", myCookie);
        
        // "/src/main/webapp/WEB-INF/views/cookie/write.jsp" 파일을 View로 지정한다.
        return "cookie/write";
    }
    
    /**
     * 쿠키를 저장하기 위한 action 페이지
     * --> 이 페이지를 "/cookie/save.do" URL에 POST 방식으로 노출시킨다.
     */
    @RequestMapping(value = "/cookie/save.do", method = RequestMethod.POST)
    public String cookieSave(HttpServletResponse response, 
            @RequestParam(value="user_input", defaultValue="") String userInput) {
                
        /** 1) 쿠키 저장하기 */
        //  쿠키 저장을 위해서는 URLEncoding 처리가 필요하다.
        if (!userInput.equals("")) {
            try {
                userInput = URLEncoder.encode(userInput, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        
        Cookie cookie = new Cookie("my_cookie", userInput);  // 저장할 쿠키 객체 생성.
        cookie.setPath("/");            // 쿠키의 유효 경로 --> 사이트 전역에 대한 설정.
        cookie.setDomain("localhost");  // 쿠키의 유효 도메인
        
        if (userInput.equals("")) {     // 쿠키 시간을 설정하지 않으면 브라우저가 동작하는 동안 유효
            cookie.setMaxAge(0);        // 쿠키 설정시간이 0이면 즉시 삭제된다.
        } else {
            cookie.setMaxAge(60);       // 값이 있다면 60초 동안 쿠키 저장
        }
        
        response.addCookie(cookie);     // 쿠키 저장

        /** 2) Spring방식의 페이지 이동. */
        // Servlet의 response.sendRedirect(url)과 동일 
        //--> "/"부터 시작할 경우 ContextPath는 자동으로 앞에 추가된다.
        return "redirect:/cookie/write.do";
    }
}
