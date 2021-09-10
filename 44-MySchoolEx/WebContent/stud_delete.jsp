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
	
	if (studno_str != null) {
		studno = Integer.parseInt(studno_str);
	}
	
	Logger logger = LogManager.getFormatterLogger("stud_delete.jsp");
	
	Student stud = new Student();
	stud.setStudno(studno);
	
	SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
	
	StudentService studentService = new StudentServiceImpl(sqlSession, logger);
	
	try {
		studentService.deleteStudent(stud);
		sqlSession.commit();
		logger.debug("데이터 삭제 완료");
	} catch (NullPointerException e) {
		logger.error("삭제된 데이터가 없습니다.");
	} catch (Exception e) {
		logger.error("데이터 삭제에 실패했습니다." + e.getMessage());
	}
	
	sqlSession.close();
	
	response.sendRedirect("stud_list.jsp");
	
%>
