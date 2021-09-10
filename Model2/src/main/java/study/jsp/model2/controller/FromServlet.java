package study.jsp.model2.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FromServlet
 */
@WebServlet("/FromServlet")
public class FromServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FromServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** 1) 페이지 헤더 설정 */
		// 브라우저에 전달되는 컨텐츠의 형식 지정
		response.setContentType("text/html; charset=utf-8");
		// 브라우저로부터 전달받는 파라미터의 인코딩 형식 지정
		request.setCharacterEncoding("utf-8");
		
		/** 2) 브라우저에게 전달될 HTML 출력하기 */
		// JSP에서 사용되던 out 내장객체를 생성한다. --> import java.io.PrintWriter;
		PrintWriter out = response.getWriter();
		
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head><meta charset='utf-8' /></head>");
		out.println("<body>");
		out.println("<h1>FromServlet 클래스 입니다.</h1>");
		
		// SubPage에게 GET 파라미터 전달하기
		out.println("<p><a href='ToServlet?num1=100&num2=200'>go to ToServlet</a></p>");
		
		// SubPage에게 POST 파라미터 전달하기
		out.println("<form method='post' action='ToServlet'>");
		out.println("<input type='text' name='user_name' />");
		out.println("<input type='submit' value='전송' />");
		out.println("</form>");
		
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
