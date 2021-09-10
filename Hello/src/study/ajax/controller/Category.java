package study.ajax.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import study.ajax.model.CategoryItem;

@WebServlet("/api/category.do")
public class Category extends HttpServlet {
	private static final long serialVersionUID = -7633185553778077154L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		String type = request.getParameter("type");
		
		List<CategoryItem> list = new ArrayList<CategoryItem>();

		switch (type) {
		case "man":
			list.add(new CategoryItem("man01", "남성정장"));
			list.add(new CategoryItem("man02", "남성캐쥬얼"));
			list.add(new CategoryItem("man03", "아우터"));
			list.add(new CategoryItem("man04", "스포츠의류"));
			list.add(new CategoryItem("man05", "구두/운동화"));
			list.add(new CategoryItem("man06", "악세사리/가방"));
			break;
		case "woman":
			list.add(new CategoryItem("woman01", "여성정장"));
			list.add(new CategoryItem("woman02", "여성캐쥬얼"));
			list.add(new CategoryItem("woman03", "아우터"));
			list.add(new CategoryItem("woman04", "스포츠의류"));
			list.add(new CategoryItem("woman05", "구두/운동화"));
			list.add(new CategoryItem("woman06", "악세사리/가방"));
			break;
		case "it":
			list.add(new CategoryItem("it01", "핸드폰/태블릿"));
			list.add(new CategoryItem("it02", "PC/노트북"));
			list.add(new CategoryItem("it03", "TV/냉장고/세탁기"));
			list.add(new CategoryItem("it04", "소형가전"));
			break;
		}

		// JSON형식의 문자열 만들기
		JSONObject json = new JSONObject();
		json.put("item", list);
		response.getWriter().print(json);
	}

}
