package study.jsp.model2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Sub
 */
// WEB상에 노출되는 URL을 sub.do로 설정한다.
@WebServlet("/sub.do")
public class Sub extends HttpServlet {
    private static final long serialVersionUID = 2597879657454404329L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 브라우저에 전달되는 컨텐츠의 형식 지정
        response.setContentType("text/html; charset=utf-8");
        // 브라우저로부터 전달받는 파라미터의 인코딩 형식 지정
        request.setCharacterEncoding("utf-8");

        // 파라미터 받기
        String num1_str = request.getParameter("num1");
        String num2_str = request.getParameter("num2");

        // 연산을 통한 새로운 데이터 생성하기
        int num1 = Integer.parseInt(num1_str);
        int num2 = Integer.parseInt(num2_str);
        int num3 = num1 + num2;

        // JSP에게 전달할 변수값들을 request 객체에 추가
        request.setAttribute("num1", num1);
        request.setAttribute("num2", num2);
        request.setAttribute("num3", num3);

        // 프로그램의 흐름을 "/WEB-INF/views/sub_get.jsp"에게 넘긴다.
        String view = "/WEB-INF/views/sub_get.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 브라우저에 전달되는 컨텐츠의 형식 지정
        response.setContentType("text/html; charset=utf-8");
        // 브라우저로부터 전달받는 파라미터의 인코딩 형식 지정
        request.setCharacterEncoding("utf-8");

        // 파라미터 받기
        String user_name = request.getParameter("user_name");

        // JSP에게 전달할 변수값들을 request 객체에 추가
        request.setAttribute("user_name", user_name);

        // 프로그램의 흐름을 "/WEB-INF/views/sub_post.jsp"에게 넘긴다.
        String view = "/WEB-INF/views/sub_post.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

}
