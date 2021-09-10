<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="java.net.URLEncoder" %>
<%
    // 파라미터의 문자열 셋을 설정한다. (페이지 상단에서 최초 1회)
    request.setCharacterEncoding("UTF-8");

    // 같은 이름의 체크박스가 하나인 경우는 일반 파라미터와 처리가 동일하다.
    // 체크박스가 선택되었다면 value 속성의 "Y"가 전달될 것이고,
    // 선택되지 않았다면 null이 된다.
    String isPopup = request.getParameter("is_popup");

    // 체크박스가 선택되었다면 60초의 유효시간을 갖는 is_popup이라는 이름의 쿠키를 생성한다.
    if (isPopup != null) {
        /** 전송된 값이 있는 경우 저장 (혹은 덮어쓰기) */
        String enc = URLEncoder.encode(isPopup, "UTF-8");    // 저장할 값에 대한 URLEncoding
        Cookie info = new Cookie("is_popup", enc);           // 쿠키 생성 (이름, 값 설정)
        info.setMaxAge(60);             // 쿠키의 유효시간(초)
        info.setPath("/");              // 쿠키가 유효한 경로 설정
        info.setDomain("localhost");    // 쿠키가 유효한 도메인 설정 --> 상용화시에는 사이트에 맞게 수정해야 함
        response.addCookie(info);       // 쿠키 저장하기
    }
%>
<script type='text/javascript'>
    window.close();  // Javascript 현재 창 닫기
</script>
