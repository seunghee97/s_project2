<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- 답변글의 큰들과 창 스타일 -->
<style type="text/css">
#modal_wrap { /*#:아이디*/
	display: none; /*보여주지 않는 것 */
	position: fixed; /*위치 고정 */
	background-color: rgba(0, 0, 0, 0.4);
	top: 0;
	left: 0;
	right: 0; /*위치를 맨위로*/
	width: 100%;
	height: 100%; /*넓이, 높이*/
	margin: auto; /*가운데 정렬*/
	z-index: 9;
}

#first {
	display: none;
	position: fixed;
	z-index: 8;
	margin: auto;
	top: 30px;
	left: 0;
	right: 0;
	width: 350px;
	height: 450px;
	background-color: rgba(212, 244, 250, 0.9)
}
</style>

<script type="text/javascript">
	function slideClick() {
		//block 만들기 : 답글을 보이게 만들겠다.
		$("#first").show()//slideDown("slow") //답글달기를 천천히 보게하기
		$("#modal_wrap").show() //전체 배경색은 바로 보이게 한다.
	}
	
	<%-- 답글을 취소 --%>
	function slide_hide(){
		$("#first").slideUp("fast")
		$("#modal_wrap").hide() //숨기겠다.
	}
	
	<%-- 답변을 저장 --%>
	function rep(){
		//ajax
		let form={}
		let arr=$("#frm").serializeArray()
		for(i=0;i<arr.length;i++){
			form[arr[i].name]=arr[i].value
		}
		$.ajax({
			url:"addReply", type:"post",data:JSON.stringify(form),
			contentType:"application/json;charset=utf-8",
			success:function(){
				alert('답글이 달렸습니다.')
				slide_hide()
				replyData()
			},error:function(){
				alert('문제 발생')
			}
		})
	}
	
	function replyData() { //답글 글 보여주기
		$.ajax({
			url:"replyData/"+${data.writeNo}, type:"get",dataType:"json",
			success:function(rep){
				let html=""
				rep.forEach(function(data) {
					let date = new Date(data.write_date)
					let dateWrite=""
					dateWrite += date.getFullYear()+"년"
					dateWrite += date.getMonth()+1 +"월"
					dateWrite += date.getDate()+"일"
					dateWrite += date.getHours()+"시"
					dateWrite += date.getMinutes()+"분"
					dateWrite += date.getSeconds()+"초"
					html+="<div align='left'>"
					html+="<b>아이디:</b>"+data.id+"님/"
					html+="<b>작성일:</b>"+dateWrite+"<br>"
					html+="<b>제목:</b>"+data.title+"<br>"
					html+="<b>내용:</b>"+data.content+"<hr></div>"
				})
				$("#reply").html(html)
			},error:function(){
				alert('데이터를 가져올 수 없습니다.')
			}
		})
	}
	
</script>
</head>
<body onload="replyData()">
	<c:import url="../default/header.jsp" />
	<div class="wrap">
		<table border="1">
			<tr>
				<th width="100">글번호</th>
				<td width="200">${data.writeNo }</td>

				<th width="100">작성자</th>
				<td width="200">${data.id }</td>
			</tr>

			<tr>
				<th>제목</th>
				<td>${data.title }</td>
				<th>등록일자</th>
				<td>${data.saveDate }</td>
			</tr>

			<tr>
				<th>내용</th>
				<td>${data.content }</td>
				<td colspan="2"><c:if test="${data.imageFileName =='nan' }">
						<b>이미지가 없습니다.</b>
					</c:if> <c:if test="${data.imageFileName !='nan' }">
						<img width="200px" height="100px"
							src="${contextPath }/board/download?imageFileName=${data.imageFileName}">
					</c:if></td>
			</tr>
			<tr>
				<td colspan="4" align="center"><c:if
						test="${data.id == loginUser }">
						<!-- 아이디와 작성자가 일치하냐 -->
						<input type="button"
							onclick="location.href='${contextPath }/board/modify_form?writeNo=${data.writeNo}'"
							value="수정하기">
						<input type="button"
							onclick="location.href='${contextPath }/board/delete?writeNo=${data.writeNo}&imageFileName=${data.imageFileName }'"
							value="삭제하기">
					</c:if> <input type="button" onclick="slideClick()" value="답글달기">
					<input type="button"
					onclick="location.href='${contextPath }/board/boardAllList'"
					value="리스트 이동"></td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="reply" align="center">답글이 없습니다.</div>
				</td>
			</tr>
		</table>
	</div>

	<!-- 답변 공간 만들기 -->
	<div id="modal_wrap">
		<!-- 답변의 큰 틀  -->
		<div id="first">
			<!-- 입력받는 창 -->
			<div style="width: 250px; margin: auto; padding-top: 20px">
				<form id="frm">
					<b>글번호:</b><input type="text" name="write_no"
						value="${data.writeNo }" readonly>
					<hr>
					<b>작성자:</b>${loginUser }
					<hr>
					<b>제목:</b><br> <input type="text" id="title" name="title"
						size="30">
					<hr>
					<b>내용:</b><br>
					<textarea rows="5" cols="30" name="content" id="content"></textarea>
					<hr>
					<button type="button" onclick="rep()">답글저장</button>
					<button type="button" onclick="slide_hide()">취소</button>
				</form>
			</div>
		</div>
	</div>

	<c:import url="../default/footer.jsp" />
</body>
</html>