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
<script type="text/javascript">
//파일 업로드
function readURL(input){
	//img input 그자체 
   var file = input.files[0]
   console.log(file)
   if(file != ""){ //파일이 있다.
      var reader = new FileReader()
      reader.readAsDataURL(file) //파일을 불러와서  reader에 저장 
      reader.onload = function(e){ //파일을 등록
         $("#preview").attr("src",e.target.result) //preview에 넣기 
      }
   }
   
}
</script>
</head>
<body>
<c:import url="../default/header.jsp" />
	<form action="${contextPath }/board/modify" enctype="multipart/form-data" method="post">
		<input type="hidden" name="writeNo" value="${data.writeNo }">
		<input type="hidden" name="originFileName" value="${data.imageFileName }">
		제목<input type="text" value="${data.title }" name="title"><hr>
		내용<textarea rows="5" cols="30" name="content">${data.content }</textarea>
		<hr>
		<!-- alt: 이미지가 존재하지 않을 때 사용한다. -->
		<img alt="이미지가 없습니다." src="${contextPath }/board/download?imageFileName=${data.imageFileName}" width="200px" height="100px" id="preview">
		<input type="file" name="imageFileName" onchange="readURL(this)">
		<hr>
		<input type="submit" value="수정하기">
		<input type="button" onclick="history.back()" value="back">
	</form>
</body>
</html>