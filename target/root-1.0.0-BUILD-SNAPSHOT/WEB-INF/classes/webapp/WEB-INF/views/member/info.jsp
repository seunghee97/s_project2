<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url="../default/header.jsp"/>
	<div class="wrap content">
		<table border="1" style="width:300px; margin:auto;"> 
			<tr>
				<th>아이디</th> <th>${info.id}</th> <th></th>
			</tr>
			<tr>
				<th>비번</th> <th>${info.pw}</th> <th></th>
			</tr>
			<tr>
				<th>주소</th> <th>${info.addr}</th> <th></th>
			</tr>
		</table>
	</div>
</body>
</html>