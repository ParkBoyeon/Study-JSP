<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	// 파라미터 수신에 사용할 문자셋 설정 (각 페이지당 최상다넹서 1회 설정)
	request.setCharacterEncoding("UTF-8");

	// 세션 유효시간 설정 (분단뒤, 기본값 5분) -> 모든 페이지마다 개별 설정함
	session.setMaxInactiveInterval(60);
	
	// 사용자 입력값 받기
	String input = request.getParameter("user_input");
	
	if(input != null && !input.equals("")) {
		// 세션 저장 (key, value 형식) --> 모든 타입의 객체를 저장할 수 있다. 
		session.setAttribute("mysession", input);
	} else {
		// 개별 세션 삭제
		session.removeAttribute("mysession");
	}
	
	// 페이지 강제 이동
	response.sendRedirect("session.jsp");
 %>