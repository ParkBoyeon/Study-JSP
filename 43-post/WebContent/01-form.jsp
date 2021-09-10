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
		<form name="myform" method="post" action="02-action.jsp" role="form">
			<fieldset>
				<legend>Post Data 전송예제</legend>
				
				<div class="form-group">
					<label for="user_name">이름</label>
					<input type="text" name="user_name" id="user_name" class="form-control"/>
					
				</div>
				<button type="submit" class="btn btn=primary">Submit</button>
			</fieldset>
		</form>
		
	</div>
</body>
</html>