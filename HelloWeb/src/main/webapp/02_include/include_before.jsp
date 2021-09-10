<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
	<style type="text/css">
		* {
			padding: 0;
			margin: 0;
		}
		.common {
			text-align: center;
			font-size: 18px;
		}
		.top {
			background-color: #ffff00;
			height: 100px;
			line-height: 100px;
		}
		.main {
			background-color: #eeeeee;
			height: 400px;
			line-height: 400px;
		}
		.bottom {
			background-color: #ff00ff;
			height: 80px;
			line-height: 80px;
		}
		
	</style>
</head>
<body>
	<header class="common top">
		<h1>웹 페이지 타이틀 영역</h1>
	</header>
	<section class="common main">
		<h1>웹 페이지 본문 영역</h1>
	</section>
	<footer class="common bottom">
		<h1>웹 페이지 하단 영역</h1>
	</footer>
</body>
</html>