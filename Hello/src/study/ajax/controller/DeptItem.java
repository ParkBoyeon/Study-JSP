package study.ajax.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/api/dept_item.do")
public class DeptItem extends HttpServlet {
	private static final long serialVersionUID = -3107452273411406252L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		int deptno = 0;
		String dname = null;
		String loc = null;
		
		try {
			deptno = Integer.parseInt(request.getParameter("deptno"));
		} catch (Exception e) {}
		
		switch (deptno) {
			case 101:  dname = "컴퓨터공학과"; 	 loc = "1호관"; break;
			case 102:  dname = "멀티미디어학과"; loc = "2호관"; break;
			case 201:  dname = "전자공학과"; 	 loc = "3호관"; break;
			case 202:  dname = "기계공학과"; 	 loc = "4호관"; break;
		}
		
		JSONObject json = new JSONObject();
		json.put("deptno", deptno);
		json.put("dname", dname);
		json.put("loc", loc);
		response.getWriter().print(json);
	}
}