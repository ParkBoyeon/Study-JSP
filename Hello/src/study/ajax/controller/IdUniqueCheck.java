package study.ajax.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/api/id_unique_check.do")
public class IdUniqueCheck extends HttpServlet {
	private static final long serialVersionUID = -4359820221573553560L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		String userId = request.getParameter("user_id");
		String result = "OK";
		
		String[] idList = {"user1", "user2", "user3", "user4"};
		for (int i=0; i<idList.length; i++) {
			// 기 가입된 아이디와 일치하는게 있다면 실패처리
			if (idList[i].equals(userId)) {
				result = "FAIL";
				break;
			}
		}

		JSONObject json = new JSONObject();
		json.put("result", result);
		response.getWriter().print(json);
	}
}
