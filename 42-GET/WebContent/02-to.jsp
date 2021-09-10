<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%

	// 파라미터 수신에 사용할 문자셋 설정 (각 페이지당 최 상단에서 1회 설정)
	request.setCharacterEncoding("UTF-8");

	// 파라미터 받기 
	String ans = request.getParameter("ans");
	
	// 화면에 출력할 결과 문자열
	String result = null;
	
	if(ans ==null) {
		// 파라미터가 URL에 명시되지 않은 경우
		result = "응답 내용이 없습니다.";
	} else {
		// 파라미터가 URL에 명시 된 경우
		int user_select = 0;
		
		try {
			user_select = Integer.parseInt(ans);
		} catch(Exception e) {}
		
		if (user_select == 300) {
			result = "정답입니다.";
		} else {
			result = "정답이 아닙니다.";
		}
	}
%>
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
	<div class='page-header'><h1>응답결과</h1></div>
		<div>
			<p><%=result %>
		</div>
		
	</div>
</body>
</html>