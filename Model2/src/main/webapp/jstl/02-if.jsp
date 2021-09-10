<%@page import="study.jsp.model2.model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	/** 서블릿을 가정한 코드 부분 */
	// Beans 객체를 request객체에 추가하기
	Member member = new Member();
	member.setName("학생");
	member.setAge(20);
	request.setAttribute("member", member);
	
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	<!-- if (true) { . . . } -->
	<c:if test="true">
		<h1>이 블록은 무조건 출력됩니다.</h1>
	</c:if>
	
	<!-- if (false) { . . . } -->
	<c:if test="false">
		<h1>이 블록은 출력되지 않습니다.</h1>
	</c:if>
	
	<!-- if (member.age > 19) { . . . } -->
	<c:if test="${member.age > 19}">
		<h1>${member.name}님은 성인 입니다.</h1>
	</c:if>

</body>
</html>