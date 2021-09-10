<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	// 세션 유효시간 설정 (분단위, 기본값 5분) -> 모든 페이지마다 개별 설정
	session.setMaxInactiveInterval(60);
	
	// "mysession"이라는 이름으로 저장된 Session 객체 추출
	// --> Object 타입으로 형변환 되어 저장되므로, 추출시에는 원래의 형태로 명시적 형변환이 필요하다.
	String mySession = (String) session.getAttribute("mysession");
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	<h1>세션 예제</h1>
	<%
		// 추출된 세션값(mySession)의 존재 여부에 따라 출력처리 분기
		if (mySession == null || mySession.equals("")) {
			out.println("<h2>mySession값 없음</h2>");
		} else {
			out.println("<h2>mySession=" + mySession + "</h2>");
		}
	%>
	<form action="session_ok.jsp" method="post">
		<label for="user_input">저장할 내용 입력</label>
		<input type="text" name="user_input" id="user_input" />
		<button type="submit">세션저장</button>
	</form>
</body>
</html>