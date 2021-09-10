<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	// 파라미터 수신에 사용할 문자셋 설정 (각 페이지당 최상단에서 1회 설정)
	request.setCharacterEncoding("UTF-8");
	
	// 파라미터 받기
	String ans = request.getParameter("ans");
	
	// 화면에 출력할 결과 문자열
	String result = null;
	
	// 값이 존재하지 않더라도 오작동하지 않을 최소한의 방어 장치
	if (ans == null || ans.equals("")) {
		String script = "<script>";
		script += "alert('값이 없습니다.');";
		script += "history.back();";
		script += "</script>";
		
		out.print(script);
		return;	// 현재 페이지 강제 종료
	}
	
	// 사용자가 선택한 값을 숫자로 변환
	int user_select = Integer.parseInt(ans);
	
	if(user_select == 300) {
		result = "정답입니다.";
	} else {
		result = "정답이 아닙니다.";
	}
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	<h1>응답결과</h1>
	<h2>선택한 값: <%=ans%></h2>
	<h3><%=result%></h3>
</body>
</html>