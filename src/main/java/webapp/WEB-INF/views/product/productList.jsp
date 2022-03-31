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
				<th>상품번호</th> <th>상품명</th> <th>상품이미지</th> <th>상품가격</th>
			</tr>
			
			<c:forEach var="dto" items="${productList }">
			<tr>
				<td>
					${dto.getId() }
				</td>
				<td>${dto.getName() }</td>
				<td>${dto.getImgName() }</td>				
				<td>${dto.getPrice() }</td>
			</tr>
			</c:forEach>
		</table>
		
		<input type="button" value="상품등록" onclick="location.href='${contextPath}/product/productForm'">
	</div>
	
</body>
</html>