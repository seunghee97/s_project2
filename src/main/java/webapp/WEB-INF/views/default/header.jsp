<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
			uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" 
		value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
* {
	margin: 0;
}

.wrap {
	width: 1000px;
	margin: auto;
}

.header {
	width: 1000px;
}

.navdiv {
	width: 100%;
	background-color: purple;
}

nav {
	background-color: purple;
	width: 1000px;
}

nav ul {
	list-style: none;
	display: flex;
	justify-content: end;
}

nav ul li {
	padding: 10px;
}

nav ul li a {
	text-decoration: none;
	color: white;
}

nav ul li a:hover {
	color: orange;
	border-bottom: 2px solid black;
	transition: all 0.25s;
	padding-bottom: 3px;
}

.title {
	font-size: 30pt;
	text-align: center;
	margin-top: 0;
	padding-bottom: 20px;
	color: burlywood;
	font-family: Gabriola;
}

.header_a {
	margin-left: 15px;
	margin-right: 15px;
}

</style>

</head>
<body>
	<div class="wrap">
		<div class="header">
			<h1 class="title" style="margin-top: 10px;">주말 시장</h1>
		</div>
	</div>
	<div class="navdiv">
		<div class="wrap">
			<nav>
				<ul>
					
					<li><a href="${contextPath }/index">HOME</a></li>
					
					<!-- 관리자 영역  -->
					<c:choose>
						<c:when test="${loginUser eq 'aaa'}">
					<li>
						<a href="${contextPath }/member/memberInfo">MEMBER_SHIP</a>
					</li>
						</c:when>
					</c:choose>
					
					<c:choose>
						<c:when test="${loginUser eq 'aaa'}">
					<li>
						<a href="${contextPath }/product/productList">Goods</a>
					</li>
						</c:when>
					</c:choose>
					
					<li>
						<a href="${contextPath }/board/boardAllList">BOARD</a>
					</li>
					
					<li>
					<c:choose>
						<c:when test="${loginUser != null}">
							<a href="${contextPath }/member/logout">LOGOUT</a>
						</c:when>
						<c:otherwise>
							<a href="${contextPath }/member/login">LOGIN</a>
						</c:otherwise>
					</c:choose>
					</li>
				</ul>
			</nav>
		</div>
	</div>
</body>
</html>