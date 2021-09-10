package study.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/post.do")
public class Post extends HttpServlet {
	private static final long serialVersionUID = -2942443909020135171L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html"); 
		
		String userId = request.getParameter("user_id");
		String userPw = request.getParameter("user_pw");
		
		if (userId == null) { userId = ""; }
		if (userPw == null) { userPw = ""; }

		PrintWriter out = response.getWriter();
		if (userId.equals("ajax") && userPw.equals("123qwe!@#")) {
			out.write("<h1 class='text-success'>Access success</h1>");	
		} else {
			out.write("<h1 class='text-danger'>Access denied</h1>");
		}
	}
}