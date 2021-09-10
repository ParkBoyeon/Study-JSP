<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="java.net.URLDecoder"%>
<%
    // 쿠키값을 저장할 문자열
    String isPopup = null;

    // 저장된 모든 쿠키 목록을 배열로 가져온다.
    Cookie[] cookies = request.getCookies();

    // 쿠키목록이 있다면?
    if (cookies != null) {
        // 가져온 배열의 길이만큼 반복
        for (int i = 0; i<cookies.length; i++) {
            // i번째 쿠키의 이름을 취득한다.
            String name = cookies[i].getName();

            // 이름이 내가 원하는 값일 경우 값을 복사한다.
            if (name.equals("is_popup")) {
                // 쿠키값을 취득한다.
                String value = cookies[i].getValue();

                // 저장된 값의 문자열 길이가 0보다 크다면?
                if (value.length() > 0) {
                    // 인코딩된 값이므로 디코딩 처리하여 원래값으로 복원한다.
                    isPopup = URLDecoder.decode(value, "UTF-8");
                }
            }
        }
    }
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Hello JSP</title>
</head>
<body>
    <h1>팝업창 열기</h1>

    <% if (isPopup == null) { %>
    <script type='text/javascript'>
        // javascript 팝업창 열기
        var popup = window.open('child.jsp', 'child-win', 'width=350, height=300');
        if (!popup) {
            alert("팝업창을 허용해 주세요.");
        }
    </script>
    <% } %>
</body>
</html>
