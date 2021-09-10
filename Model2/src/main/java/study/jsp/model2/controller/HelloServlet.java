package study.jsp.model2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 * HelloServletMain 서블릿 클래스.
 * 모든 서블릿은 javax.servlet.http.HttpServlet을 상속받아야 한다.
 */
// 이 클래스를 URL에 노출시키기 위한 경로
// --> http://localhost:8080/프로젝트명/HelloServlet
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	
	/** 객체 직렬화 아이디 --> 이클립스가 생성한 값을 사용하게 된다. */
	// private static final long serialVersionUID = 1L;
	private static final long serialVersionUID = -5401482623263289310L;

    /**
     * @see HttpServlet#HttpServlet()
     * 생성자 (특별한 경우가 아니면 사용 안함)
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 브라우저가 별도의 파라미터 없이 접속되거나 HTTP GET 방식으로 접근한 경우 실행된다. 
	 * @param request - JSP의 request 내장객체
	 * @param response - JSP의 response 내장객체
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 브라우저가 HTTP POST 방식으로 접근한 경우 실행된다. 
	 * 기본 코드는 현재 클래스의 doGet() 메서드를 호출해서 프로그램의 흐름을 
	 * doGet() 메서드 한 곳에서만 처리하도록 제시하고 있다.
	 * @param request - JSP의 request 내장객체
	 * @param response - JSP의 response 내장객체
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
