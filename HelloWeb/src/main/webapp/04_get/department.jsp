<%@page import="study.helloweb.model.Department"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	request.setCharacterEncoding("UTF-8");				// 파라미터 수신에 사용할 문자셋 설정 (각 페이지당 최상단에서 1회 설정)
	String deptno = request.getParameter("deptno");		// 링크를 통해 전달된 파라미터 추출
	Department department = null;						// 수신된 deptno값에 따라서 결과를 저장할 객체
	
	if (deptno != null) {					// 파라미터가 없는 경우를 대비해야하므로 deptno값이 null이 아닌 경우만 수행
		department = new Department(); 		// 데이터를 저장할 객체를 할당
		
		if (deptno.equals("101")) {			// deptno가 정해진 볌위 안에서만 동작하도록 if문을 구성
			department.setDeptno(101);
			department.setDname("컴퓨터공학과");
			department.setLoc("1호관");
		} else if (deptno.equals("102")) {
			department.setDeptno(102);
			department.setDname("멀티미디어학과");
			department.setLoc("2호관");
		} else if (deptno.equals("201")) {
			department.setDeptno(201);
			department.setDname("전자공학과");
			department.setLoc("3호관");
		} else if (deptno.equals("202")) {
			department.setDeptno(202);
			department.setDname("기계공학과");
			department.setLoc("4호관");
		}
	}
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	
	<h1>학과정보</h1>
	<!-- 메뉴 제시 영역 -->
	<a href="<%=request.getContextPath() %>/04_get/department.jsp?deptno=101">컴퓨터공학과</a> |
	<a href="<%=request.getContextPath() %>/04_get/department.jsp?deptno=102">멀티미디어학과</a> |
	<a href="<%=request.getContextPath() %>/04_get/department.jsp?deptno=201">전자공학과</a> |
	<a href="<%=request.getContextPath() %>/04_get/department.jsp?deptno=202">기계공학과</a>
	
	<hr />
	
	<!-- 결과표시 영역 -->
	<% if (department == null) {%>
	<h2>메뉴를 선택하세요</h2>
	<%} else { %>
	<table>
		<tr>
			<th>학과번호</th>
			<th><%=department.getDeptno() %></th>
		</tr>
		<tr>
			<th>학과이름</th>
			<th><%=department.getDname() %></th>
		</tr>
		<tr>
			<th>학과위치</th>
			<th><%=department.getLoc() %></th>
		</tr>
	</table>
	<%} %>	<!-- end if -->
</body>
</html>