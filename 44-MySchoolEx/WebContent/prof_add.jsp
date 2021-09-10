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
		<h1 class="page-header">교수추가</h1>
		<form name="myform" method="post" action="prof_add_ok.jsp">
			<div class="form-group">
				<label for="name">이름</label>
				<input type="text" name="name" id="name" class="form-control" />
			</div>
			<div class="form-group">
				<label for="userid">아이디</label>
				<input type="text" name="userid" id="userid" class="form-control" />
			</div>
			<div class="form-group">
				<label for="position">직급</label>
				<input type="text" name="position" id="position" class="form-control" />
			</div>
			<div class="form-group">
				<label for="sal">급여</label>
				<input type="text" name="sal" id="sal" class="form-control" />
			</div>
			<div class="form-group">
				<label for="hiredate">입사일</label>
				<input type="text" name="hiredate" id="hiredate" class="form-control" />
			</div>
			<div class="form-group">
				<label for="comm">보직수당</label>
				<input type="text" name="comm" id="comm" class="form-control" />
			</div>
			<div class="form-group">
				<label for="deptno">학과번호</label>
				<input type="text" name="deptno" id="deptno" class="form-control" />
			</div>

			<button type="submit" class="btn btn-primary btn-block">
				작성완료</button>
	    </form>
	</div>
</body>
</html>