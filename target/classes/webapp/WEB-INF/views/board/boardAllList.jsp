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
	<div class="wrap" align="center">
	<table border="1">
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>제목</th>
			<th>날짜</th>
			<th>조회수</th>
			<th>파일이름</th>
		</tr>
		<c:if test="${boardList.size()==0 }">
			<tr><th colspan="6">등록된 글이 없습니다.</th></tr>
		</c:if>
		
		<c:forEach items="${boardList }" var="dto">
			<tr>
				<td>${dto.writeNo }</td>
				<td>${dto.id }</td>
				<td> 
					<a href="${contextPath }/board/contentView?writeNo=${dto.writeNo}">${dto.title }</a>
				</td>
				<td>${dto.saveDate }</td>
				<td>${dto.hit }</td>
				<td>${dto.imageFileName }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="6" align="center">
				<a href="${contextPath }/board/writeForm">글작성</a>
			</td>
		</tr>
	</table>
	</div>
	<c:import url="../default/footer.jsp"/>
</body>
</html>