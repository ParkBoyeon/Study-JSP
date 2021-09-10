<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page import="study.helloweb.model.Member"%>
<%
    // 파라미터 수신에 사용할 문자셋 설정 (각 페이지당 최 상단에서 1회 설정)
    request.setCharacterEncoding("UTF-8");

    // 현재 시스템에 가입되어 있는 회원정보를 가정하여 Beans객체 생성
    Member member = new Member();
    member.setUserId("jspuser");
    member.setUserPw("123qwe!@#");
    member.setUserName("JSP학생");
    member.setEmail("jspuser@itpaper.co.kr");

    // 사용자 입력값
    String userId = request.getParameter("user_id");
    String userPw = request.getParameter("user_pw");

    // 필수 값의 존재여부 검사
    if (userId == null || userId.equals("")) {
        // 메시지 박스를 출력하고 이전 페이지로 이동하도록 자바스크립트 출력
        out.print("<script>alert('아이디를 입력하세요');history.back();</script>");
        // 현재 페이지의 실행 중단
        return;
    }

    if (userPw == null || userPw.equals("")) {
        out.print("<script>alert('비밀번호를 입력하세요');history.back();</script>");
        return;
    }

    // 아이디와 비밀번호가 가입된 정보와 일치하는지 검사
    // --> 여기서는 특정 문자열과 동일한 경우 가입된 아이디로 판단.
    if (!userId.equals(member.getUserId()) || !userPw.equals(member.getUserPw())) {
        out.print("<script>alert('아이디나 비밀번호가 잘못되었습니다.');history.back();</script>");
        return;
    }

    // 모든 조건을 충족할 경우 로그인 처리 -> 세션 생성
    session.setAttribute("login_info", member);

    // 원래 페이지로 돌아가기
    response.sendRedirect("login.jsp");
%>