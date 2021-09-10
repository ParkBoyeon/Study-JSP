<%@page import="java.util.HashMap"%>
<%@page import="study.jsp.model2.model.Member"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// Collection Map 객체 생성
	Map<String, Member> memberMap = new HashMap<String, Member>();

	// Map 객체게 JavaBeans의 객체를 이름표를 적용하여 추가한다.
	Member mem1 = new Member();
	mem1.setName("학생1");
	mem1.setAge(20);
	memberMap.put("mem1", mem1);
	
	Member mem2 = new Member();
	mem2.setName("학생2");
	mem2.setAge(20);
	memberMap.put("mem2", mem2);
	
	Member mem3 = new Member();
	mem3.setName("학생3");
	mem3.setAge(20);
	memberMap.put("mem3", mem3);
	
	// Map 객체를 request객체에 등록한다. 
	request.setAttribute("member_map", memberMap);
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	<ul>
		<c:forEach var="k" items="${member_map}" varStatus="status">
			<li>
				member[${status.index}] ${k.key} = ${k.value.name}/${k.value.age}
			</li>
		</c:forEach>
	</ul>
</body>
</html>