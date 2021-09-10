package study.jsp.model2.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ToServlet
 */
@WebServlet("/ToServlet")
public class ToServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 브라우저에 전달되는 컨텐츠의 형식 지정
        response.setContentType("text/html; charset=utf-8");
        // 브라우저로부터 전달받는 파라미터의 인코딩 형식 지정
        request.setCharacterEncoding("utf-8");

        // GET 파라미터 받기
        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");

        // 파라미터를 통한 연산 수행하기
        int num3 = Integer.parseInt(num1) + Integer.parseInt(num2);
        String result = String.format("%s + %s = %d", num1, num2, num3);

        // 웹 페이지에 출력을 위한 out객체 생성.
        PrintWriter out = response.getWriter();

        // HTML 결과 출력하기
        out.println("<!doctype html>");
        out.println("<html>");
        out.println("<head><meta charset='utf-8' /></head>");
        out.println("<body><h1>" + result + "</h1></body>");
        out.println("</html>");
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

        // POST 파라미터 받기
        String user_name = request.getParameter("user_name");

        // 웹 페이지에 출력을 위한 out객체 생성.
        PrintWriter out = response.getWriter();
        out.println("<!doctype html>");
        out.println("<html>");
        out.println("<head><meta charset='utf-8' /></head>");
        out.println("<body><h1>" + user_name + "님 안녕하세요.</h1></body>");
        out.println("</html>");
    }

}
