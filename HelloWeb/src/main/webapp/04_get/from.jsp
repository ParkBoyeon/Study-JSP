<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	<h1>Q. 100+200=?</h1>
	<ol>
		<li><a href='<%=request.getContextPath()%>/04_get/to.jsp?ans=100'>100</a></li>
		<li><a href='<%=request.getContextPath()%>/04_get/to.jsp?ans=200'>200</a></li>
		<li><a href='<%=request.getContextPath()%>/04_get/to.jsp?ans=300'>300</a></li>
		<li><a href='<%=request.getContextPath()%>/04_get/to.jsp?ans=400'>400</a></li>
		<li><a href='<%=request.getContextPath()%>/04_get/to.jsp?ans=500'>500</a></li>
	</ol>
</body>
</html>