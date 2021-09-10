<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
	<!-- Twitter Bootstrap3 & jQuery -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1 class="page-header">학생추가</h1>
		<form name="myform" method="post" action="stud_add_ok.jsp">
			<div class="form-group">
				<label for="name">이름</label>
				<input type="text" name="name" id="name" class="form-control" />
			</div>
			<div class="form-group">
				<label for="userid">아이디</label>
				<input type="text" name="userid" id="userid" class="form-control" />
			</div>
			<div class="form-group">
				<label for="grade">학년</label>
				<input type="text" name="grade" id="grade" class="form-control" />
			</div>
			<div class="form-group">
				<label for="idnum">주민번호</label>
				<input type="text" name="idnum" id="idnum" class="form-control" />
			</div>
			<div class="form-group">
				<label for="birthdate">생년월일</label>
				<input type="text" name="birthdate" id="birthdate" class="form-control" />
			</div>
			<div class="form-group">
				<label for="tel">전화번호</label>
				<input type="text" name="tel" id="tel" class="form-control" />
			</div>
			<div class="form-group">
				<label for="height">키</label>
				<input type="text" name="height" id="height" class="form-control" />
			</div>
			<div class="form-group">
				<label for="weight">몸무게</label>
				<input type="text" name="weight" id="weight" class="form-control" />
			</div>
			<div class="form-group">
				<label for="deptno">학과번호</label>
				<input type="text" name="deptno" id="deptno" class="form-control" />
			</div>
			<div class="form-group">
				<label for="profno">담당교수의 일렬번호</label>
				<input type="text" name="profno" id="profno" class="form-control" />
			</div>

			<button type="submit" class="btn btn-primary btn-block">
				작성완료</button>
	    </form>
	</div>
</body>
</html>