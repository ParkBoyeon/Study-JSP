<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Hello JSP</title>
</head>
<body>
    <h1>교수정보</h1>
    <p>교수번호: ${output.profno}</p>
    <p>교수이름: ${output.name}</p>
    <p>아이디: ${output.userid}</p>
    <p>직급: ${output.position}</p>
    <p>급여: ${output.sal}</p>
    <p>입사일: ${output.hiredate}</p>
    <p>보직수당: ${output.comm}</p>
    <p>학과이름: ${output.dname}</p>
    <p>학과위치: ${output.loc}</p>
    <hr />
    <a href="${pageContext.request.contextPath}/professor/list.do">[목록보기]</a>
    <a href="${pageContext.request.contextPath}/professor/add.do">[교수추가]</a>
    <a href="${pageContext.request.contextPath}/professor/edit.do?profno=${output.profno}">[교수수정]</a>
    <a href="#" id="deleteProfessor" data-profno="${output.profno}" data-name="${output.name}" data-position="${output.position}">[교수삭제]</a>
    
     <!--Google CDN 서버로부터 jQuery 참조 -->
    <script src="//ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- jQuery Ajax Form plugin CDN -->
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js"></script>
    <!-- jQuery Ajax Setup -->
    <script src="${pageContext.request.contextPath}/assets/plugins/ajax/ajax_helper.js"></script>
    <!-- User Code -->
    <script>
    $(function() {
       // delete 메서드로 Ajax 요청 --> <form> 전송이 아니므로 직접 구현한다.
       $.delete("${pageContext.request.contextPath}/professor". {
    	   "profno" : profno
       }, function(json) {
    	   if (json.rt == "OK") {
    		   alert("삭제되었습니다");
    		   // 삭제 완료 후 목록 페이지로 이동
    		   window.location ="${pageContext.request.contextPath}/professor_ajax/list.do";
    	   }
       });
    });
    </script>
</body>
</html>
