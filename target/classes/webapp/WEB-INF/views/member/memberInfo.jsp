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
	<c:import url="../default/header.jsp"/>
	<div class="wrap content">
		<table border="1" class="table table-bordered">
			<tr>
				<th>아이디</th> <th>비번</th> <th>주소</th>
			</tr>
			<c:forEach var="dto" items="${memberlist }">
			<tr>
				<td>
					<a href="${contextPath }/member/info?id=${dto.getId()}">
						${dto.getId() }
					</a>
				</td>
				<td>${dto.getPw() }</td>
				<td>${dto.getAddr() }</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>