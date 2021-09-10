<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	int level = 3;
	int first = 1;
	int last = 9;
	
	request.setAttribute("level", level);
	request.setAttribute("first", first);
	request.setAttribute("last", last);
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	<c:forEach var="i" begin="${first}" end="${last}" varStatus="status">
		<p>[${status.count}/${last}] ${level} x ${i} = ${level * i}</p>
	</c:forEach>
</body>
</html>