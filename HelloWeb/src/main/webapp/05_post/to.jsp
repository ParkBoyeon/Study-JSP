<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	// 파라미터의 문자열 셋을 설정한다. (페이지 상단에서 최초 1회)
	request.setCharacterEncoding("UTF-8");
	
	// <input>태그의 name속성값을 사용하여 사용자의 입력값을 받는다.
	String userName = request.getParameter("user_name");
	String age = request.getParameter("age");
	String gender = request.getParameter("gender");
	
	// 체크박스와 같이 name 속성이 동일한 요소가 여러개인 경우 파라미터를 배열로 리턴받는다.
	// -> 반드시 체크박스일 필요는 없다. text 필드도 name 속성이 동일한 항목들이 여러개라면 배열로 처리 가능
	String[] favor = request.getParameterValues("favor[]");
	
	// 체크박스의 선택값이 저장될 문자열
	// -> favor 배열의 각 원소를 첫번째 파라미터로 설정한 구분값을 적용하여 하나의 문자열로 연결
	String colors = String.join(", ", favor);
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	<h1>이름 : <%=userName %></h1>
	<h1>나이 : <%=age %></h1>
	<h1>성별 : <%=gender %></h1>
	<h1>취향 : <%=colors %></h1>
</body>
</html>