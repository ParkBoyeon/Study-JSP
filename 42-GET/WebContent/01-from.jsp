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
	<div class='page-header'><h1>Q. 100+200=?</h1></div>
		<div>
			<a href='02-to.jsp?ans=100' class='btn btn-default'>100</a>
			<a href='02-to.jsp?ans=200' class='btn btn-primary'>200</a>
			<a href='02-to.jsp?ans=300' class='btn btn-info'>300</a>
			<a href='02-to.jsp?ans=400' class='btn btn-warning'>400</a>
			<a href='02-to.jsp?ans=500' class='btn btn-danger'>500</a>
		</div>
		
	</div>
</body>
</html>