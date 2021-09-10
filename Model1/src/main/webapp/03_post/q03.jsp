<%@page import="study.jsp.model1.helper.RegexHelper"%>
<%@page import="study.jsp.model1.helper.WebHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
// WebHelper 객체 생성
WebHelper webHelper = WebHelper.getInstance(request, response);

// RegexHelper 객체 생성
RegexHelper regexHelper = RegexHelper.getInstance();

// POST 파라미터 수신. 값이 없을 경우 기본값 null이 리턴됨
String userName = request.getParameter("user_name");
String userEmail = request.getParameter("user_email");
String userTel = request.getParameter("user_tel");
String gender = request.getParameter("gender");

// 이름에 대한 값이 없다면 메시지 출력 후 이전 페이지로 강제 이동 후 종료 (return)
if (!regexHelper.isValue(userName)) {
	webHelper.redirect(null, "이름을 입력하세요.");
	return;
}

// 이름이 한글 이외의 글자가 포함되어 있다면 메시지 출력 후 이전 페이지로 강제 이동 후 종료 (return)
if (!regexHelper.isKor(userName)) {
	webHelper.redirect(null, "이름은 한글만 입력 가능합니다.");
	return;
}

// 이메일에 대한 값이 없다면 메시지 출력 후 이전 페이지로 강제 이동 후 종료 (return)
if (!regexHelper.isValue(userEmail)) {
	webHelper.redirect(null, "이메일을 입력하세요.");
	return;
}

// 이메일 형식이 잘못되었다면 메시지 출력 후 이전 페이지로 강제 이동 후 종료 (return)
if (!regexHelper.isEmail(userEmail)) {
	webHelper.redirect(null, "이메일 형식이 잘못되었습니다.");
	return;
}

// 연락처가 입력되지 않았다면 메시지 출력 후 이전 페이지로 강제 이동 후 종료 (return)
if (!regexHelper.isValue(userTel)) {
	webHelper.redirect(null, "연락처를 입력하세요.");
	return;
}

// 연락처가 핸드폰 형식도 아니고 집전화 형식도 아니라면 메시지 출력 후 이전 페이지로 강제 이동 후 종료 (return)
if (!regexHelper.isCellPhone(userTel) && !regexHelper.isTel(userTel)) {
	webHelper.redirect(null, "연락처 형식이 잘못되었습니다.");
	return;
}

// 성별의 경우 입력값이 둘 중 하나이므로 그 외의 경우는 걸러내야 한다.
if (!gender.equals("남자") && !gender.equals("여자")) {
	webHelper.redirect(null, "성별이 올바르지 않습니다.");
	return;
}

%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	<h1>간단 설문조사 3/3</h1>
	<h2>취미를 선택해 주세요</h2>
	
	<form action="<%=request.getContextPath()%>/03_post/result.jsp" name="myform" method="post">
		
		<!-- 이전 화면에서 넘어온 값에 대한 상태 유지 -->
		<input type='hidden' name='user_name' value='<%=userName%>' /> 
		<input type='hidden' name='user_email' value='<%=userEmail%>' /> 
		<input type='hidden' name='user_tel' value='<%=userTel%>' />
		<input type='hidden' name='gender' value='<%=gender%>' />
		
		<!-- 설문항목 -->
		<div>
			<label><input type="checkbox" name="hobby[]" value="음악감상" checked> 음악감상 </label>
			<label><input type="checkbox" name="hobby[]" value="영화감상"> 영화감상 </label>
			<label><input type="checkbox" name="hobby[]" value="게임"> 게임 </label>
		</div>
		<button type="submit">다음단계</button>
	</form>
	
	
</body>
</html>