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
	
	if (profno_str != null) {
		profno = Integer.parseInt(profno_str);
	}
	
	Logger logger = LogManager.getFormatterLogger("prof_delete.jsp");
	
	Professor prof = new Professor();
	prof.setProfno(profno);
	
	SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
	
	ProfessorService professorService = new ProfessorServiceImpl(sqlSession, logger);
	
	try {
		professorService.deleteProfessor(prof);
		sqlSession.commit();
		logger.debug("데이터 삭제 완료");
	} catch (NullPointerException e) {
		logger.error("삭제된 데이터가 없습니다.");
	} catch (Exception e) {
		logger.error("데이터 삭제에 실패했습니다." + e.getMessage());
	}
	
	sqlSession.close();
	
	response.sendRedirect("prof_list.jsp");
	
%>
