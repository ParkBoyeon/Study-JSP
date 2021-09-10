<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	int point = 82;
	request.setAttribute("point", point);
	
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	<c:choose>
		<c:when test="${point > 90 && point <= 100}">
			<h1>A학점 입니다.</h1>
		</c:when>
		
		<c:when test="${point > 80 && point <= 90}">
			<h1>B학점 입니다.</h1>
		</c:when>
		
		<c:when test="${point > 70 && point <= 80}">
			<h1>C학점 입니다.</h1>
		</c:when>
		
		<c:otherwise>
			<h1>F학점 입니다.</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>