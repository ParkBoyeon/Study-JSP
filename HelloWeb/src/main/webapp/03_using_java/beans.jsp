<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page import="study.helloweb.model.Department"%>
<%
    Department department = new Department();
    department.setDeptno(101);
    department.setDname("컴퓨터공학과");
    department.setLoc("공학관");
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	<ul>
		<li><%=department.getDeptno() %></li>
		<li><%=department.getDname() %></li>
		<li><%=department.getLoc() %></li>
	</ul>
	<p><%=department.toString() %></p>
</body>
</html>