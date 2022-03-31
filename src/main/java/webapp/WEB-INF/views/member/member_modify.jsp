<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" 
		value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url="../default/header.jsp" />
	<form action="${contextPath }/member/modify" method="post">
		아이디<input type="text" name="id" value="${data.id }"><hr>
		주소<input type="text" name='addr' value="${data.addr }"><hr>
		<input type="submit" value="수정하기"><hr>
		<input type="button" onclick="history.back()" value="되돌아가기"><hr>
	</form>
</body>
</html>