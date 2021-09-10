package study.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/get.do")
public class Get extends HttpServlet {
	private static final long serialVersionUID = -2942443909020135171L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html"); 
		
		int num1 = 0;
		int num2 = 0;
		
		try {
			num1 = Integer.parseInt(request.getParameter("num1"));
			num2 = Integer.parseInt(request.getParameter("num2"));
		} catch (Exception e) {}
		
		int num3 = num1 + num2;
		
		String result = String.format("%d + %d = %d", num1, num2, num3);
		PrintWriter out = response.getWriter();
		out.print("<h1 class='text-danger'>" + result + "</h1>");
	}
}





