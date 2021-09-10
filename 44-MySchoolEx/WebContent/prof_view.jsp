<%@page import="study.java.myschool.service.impl.ProfessorServiceImpl"%>
<%@page import="study.java.myschool.service.ProfessorService"%>
<%@page import="study.java.myschool.MyBatisConnectionFactory"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="study.java.myschool.model.Professor"%>
<%@page import="org.apache.logging.log4j.LogManager"%>
<%@page import="org.apache.logging.log4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	request.setCharacterEncoding("utf-8");
	String profno_str = request.getParameter("profno");
	int profno = 0;
	
	if(profno_str != null) {
		profno = Integer.parseInt(profno_str);
	}
	
	Logger logger = LogManager.getFormatterLogger("prof_view.jsp");
	
	Professor prof = new Professor();
	prof.setProfno(profno);
	
	SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
	
	ProfessorService professorService = new ProfessorServiceImpl(sqlSession, logger);
	Professor item = null;
	
	
	try {
		item = professorService.getProfessorItem(prof);
	} catch (NullPointerException e) {
		logger.error("조회된 데이터가 없습니다.");
	} catch (Exception e) {
		logger.error("데이터 조회에 실패했습니다." + e.getMessage());
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
		<h1 class="page-header">교수정보</h1>
		<ul class="list-group">
			<% if(item != null) { %>
			<li class="list-group-item">교수번호:<%=item.getProfno() %></li>
			<li class="list-group-item">이름:<%=item.getName() %></li>
			<li class="list-group-item">아이디:<%=item.getUserid() %></li>
			<li class="list-group-item">직급:<%=item.getPosition() %></li>
			<li class="list-group-item">급여:<%=item.getSal() %></li>
			<li class="list-group-item">입사일:<%=item.getHiredate() %></li>
			<li class="list-group-item">보직수당:<%=item.getComm() %></li>
			<li class="list-group-item">학과번호:<%=item.getDeptno() %></li>
			<%} %>
		</ul>
		
		<!-- 버튼들 -->
		<p class="text-center">
			<a href="prof_list.jsp" class="btn btn-success">목록</a>
			<a href="prof_add.jsp" class="btn btn-primary">추가</a>
			<a href="prof_delete.jsp?profno=<%=profno%>" class="btn btn-danger">삭제</a>
		</p>
	</div>
</body>
</html>
<%
sqlSession.close();
%>