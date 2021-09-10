package study.ajax.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/api/login_ok.do")
public class LoginOk extends HttpServlet {
	private static final long serialVersionUID = -2942443909020135171L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json"); 
		
		String userId = request.getParameter("user_id");
		String userPw = request.getParameter("user_pw");
		
		if (userId == null) { userId = ""; }
		if (userPw == null) { userPw = ""; }

		String result = "FAIL";
		if (userId.equals("ajax") && userPw.equals("123qwe!@#")) {
			result = "OK";	
		}
		
		JSONObject json = new JSONObject();
		json.put("result", result);
		response.getWriter().print(json);
	}
}