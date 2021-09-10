package study.jsp.model2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study.jsp.model2.model.Member;

/**
 * Servlet implementation class SimpleJstl
 */
@WebServlet("/SimpleJstl")
public class SimpleJstl extends HttpServlet {
	
	private static final long serialVersionUID = 7169072422760914445L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public SimpleJstl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 브라우저에 전달되는 컨텐츠의 형식 지정
		response.setContentType("text/html; charset=utf-8");
		// 브라우저로부터 전달받는 파라미터의 인코딩 형식 지정
		request.setCharacterEncoding("utf-8");
		
		// 문자열 변수를 View에 전달함
		String message = "Hello JSTL";
		request.setAttribute("my_message", message);
		
		// 정수형 변수를 View에 전달함
		int num1 = 7;
		int num2 = 9;
		request.setAttribute("my_num1", num1);
		request.setAttribute("my_num2", num2);
		
		// Pojo 클래스에 대한 객체를 View에 전달함
		Member member = new Member();
		member.setName("학생");
		member.setAge(20);
		request.setAttribute("my_member", member);
		
		// 현재 서블릿의 요청을 JSP에게 전달하는 객체 생성
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/simple_jstl.jsp");
		
		// 현재 서블릿의 프로그램 흐름을 "/jstl/simple_mvc.jsp" 페이지에게 넘긴다.
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
