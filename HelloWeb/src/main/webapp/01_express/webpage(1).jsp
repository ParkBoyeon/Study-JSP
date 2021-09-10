<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%
    int a = 100;    // 정수형 변수 a에 100 할당
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
    a += 100;       // a를 100증가. 이전 블록에서 생성한 변수를 사용하게 된다.
%>
</head>
<body>
<%
    out.println(a);   // 변수값 출력하기. 200이 출력된다.
%>
</body>
</html>
