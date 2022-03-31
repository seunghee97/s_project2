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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- 이미지선택시 이미지를 보이게 만들기 -->
<script type="text/javascript">
		function readURL(input) {
			//파일의 위치 알기
			var file = input.files[0]
			if(file!=0){
				var reader = new FileReader() 
				reader.readAsDataURL(file) //file를 불러와서 reader에 저장 
				reader.onload=function(e){
					$("#preview").attr("src",e.target.result) //img태그에 저장한다.
				}
			}
		}

</script>
</head>
<body>
<c:import url="../default/header.jsp"/>
<div style="width: 400px; margin: auto">
	<h1>글쓰기</h1>
	<br>
	<form method="post" action="${contextPath }/board/writeSave" enctype="multipart/form-data">
	<b>작성자</b><br>
		<input type="text" name="id" value="${loginUser }" readonly><br><hr>
	<b>제목</b><br>
		<input type="text" name="title" size="50"><br><hr>
	<b>내용</b><br>
		<textarea rows="10" cols="50" name="content"></textarea><br>
	<b>이미지 파일 첨부</b><br>
		<!-- 파일을 선택 -->
		<input type="file" name="image_file_name" onchange="readURL(this)"> <!-- this는 input에 대한 정보 -->
		<!-- 선택한 이미지를 보여주기 -->
		<img alt="이미지를 선택하세요" src="#" id="preview" width="100" height="100">
		<hr>
		<input type="submit" value="글 저장">
		<input type="button" value="목록이동" onclick="location.href='${contextPath}/board/boardAllList'">
	</form>
</div>
<c:import url="../default/footer.jsp"/>
</body>
</html>