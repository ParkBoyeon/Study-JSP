<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
	
</head>
<body>
	<h1>POST 전송</h1>
	<form action="to.jsp" name="myform" method="post">
		<div>이름: <input type="text" name="user_name" /></div>
		<div>나이: <input type="text" name="age" /></div>
		<div>성별: 
			<label><input type="radio" name="gender" value="M" checked>남자</label>
			<label><input type="radio" name="gender" value="F">여자</label>
		</div>
		<div>취향: 
			<label><input type="checkbox" name="favor[]" value="red">빨강</label>
			<label><input type="checkbox" name="favor[]" value="green">초록</label>
			<label><input type="checkbox" name="favor[]" value="blue">파랑</label>
		</div>
		<button type="submit">전송</button>
	</form>
</body>
</html>