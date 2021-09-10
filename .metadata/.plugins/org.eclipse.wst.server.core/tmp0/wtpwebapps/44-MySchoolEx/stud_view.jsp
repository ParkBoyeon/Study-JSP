<%@page import="study.java.myschool.service.impl.StudentServiceImpl"%>
<%@page import="study.java.myschool.service.StudentService"%>
<%@page import="study.java.myschool.MyBatisConnectionFactory"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="study.java.myschool.model.Student"%>
<%@page import="org.apache.logging.log4j.LogManager"%>
<%@page import="org.apache.logging.log4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	request.setCharacterEncoding("utf-8");
	String studno_str = request.getParameter("studno");
	int studno = 0;
	
	if(studno_str != null) {
		studno = Integer.parseInt(studno_str);
	}
	Logger logger = LogManager.getFormatterLogger("stud_view.jsp");
	
	Student stud = new Student();
	stud.setStudno(studno);
	
	SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
	
	StudentService studentService = new StudentServiceImpl(sqlSession, logger);
	Student item = null;
	
	try {
		item = studentService.getStudentItem(stud);
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
		<h1 class="page-header">학생정보</h1>
		<ul class="list-group">
			<% if(item != null) { %>
			<li class="list-group-item">학생번호:<%=item.getStudno() %></li>
			<li class="list-group-item">이름:<%=item.getName() %></li>
			<li class="list-group-item">아이디:<%=item.getUserid() %></li>
			<li class="list-group-item">학년:<%=item.getGrade() %></li>
			<li class="list-group-item">주민번호:<%=item.getIdnum() %></li>
			<li class="list-group-item">생년월일:<%=item.getBirthdate() %></li>
			<li class="list-group-item">전화번호:<%=item.getTel() %></li>
			<li class="list-group-item">키:<%=item.getHeight() %></li>
			<li class="list-group-item">몸무게:<%=item.getWeight() %></li>
			<li class="list-group-item">학과번호:<%=item.getDeptno() %></li>
			<li class="list-group-item">담당교수의 일련번호:<%=item.getProfno() %></li>
			<%} %>
		</ul>
		
		<!-- 버튼들 -->
		<p class="text-center">
			<a href="stud_list.jsp" class="btn btn-success">목록</a>
			<a href="stud_add.jsp" class="btn btn-primary">추가</a>
			<a href="stud_delete.jsp?studno=<%=studno%>" class="btn btn-danger">삭제</a>
		</p>
	</div>
</body>
</html>
<%
sqlSession.close();
%>