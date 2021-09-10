<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page import="java.net.URLEncoder"%>
<%
    // 파라미터 수신에 사용할 문자셋 설정 (각 페이지당 최 상단에서 1회 설정)
    request.setCharacterEncoding("UTF-8");

    // <input>태그의 name속성값을 사용하여 사용자의 입력값을 받는다.
    String input = request.getParameter("user_input");

    // 입력값의 존재 여부에 따라 쿠키를 저장하거나 삭제
    if (input != null) {
        /** 입력값이 존재할 경우 쿠키 저장 (혹은 덮어쓰기) */
        input = URLEncoder.encode(input, "UTF-8");    // 저장할 값에 대한 URLEncoding
        Cookie info = new Cookie("mycookie", input);  // 쿠키 생성 (이름, 값 설정)
        info.setMaxAge(60);          // 쿠키의 유효시간(초) - 지정하지 않을 경우 브라우저를 닫으면 즉시 삭제
        info.setPath("/");           // 쿠키가 유효한 경로 설정 - 사이트 최상단 디렉토리 지정(사이트 전역에서 유효)
        info.setDomain("localhost"); // 쿠키가 유효한 도메인 설정 --> 상용화시에는 사이트에 맞게 수정해야 함
        response.addCookie(info);    // 쿠키 저장하기
    } else {
        /** 입력값이 존재하지 않을 경우 쿠키 삭제 */
        // 유효시간을 과거 시점으로 지정하면 즉시 삭제된다.
        // 그 밖의 정보(유효경로, 도메인)는 저장시에 설정한 값과 동일해야 한다.
        Cookie info = new Cookie("mycookie", null);
        info.setMaxAge(-1);
        info.setPath("/");
        info.setDomain("localhost");
        response.addCookie(info);
    }

    // 결과 확인을 위한 페이지 강제 이동
    response.sendRedirect("cookie.jsp");
%>
