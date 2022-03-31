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

<script
  src="https://code.jquery.com/jquery-3.6.0.min.js"
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  crossorigin="anonymous"></script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
function daumPost(){
    new daum.Postcode({
        oncomplete: function(data) {
           console.log("선택"+data.userSelectedType)
           console.log("로드"+data.roadAddress)
           console.log("지번"+data.jibunAddress)
           console.log("우편번호"+data.zonecode)
           var addr=""
           if(data.userSelectedType=='R'){
        	   addr=data.roadAddress
           }else{
        	   addr=data.jibunAddress
           }
           document.getElementById("addr1").value = data.zonecode
           $("#addr2").val(addr) //#: id, . : class 
           $("#addr3").focus()
        }
    }).open();
}
function register() {
	var id = $("#id").val()
	if(id==""){
		alert("아이디는 필수 항목입니다.")
		$("#id").focus;
	}else{
		var addr=$("#addr1").val()+"/"+$("#addr2").val()+"/"+$("#addr3").val()
		$("#addr1").val(addr)
		fo.submit()
	}
}
function pwdchk() {
	var pw1=$("#pw1").val()
	var pw2=$("#pw2").val()
	if(pw1==pw2){
		$("#label").html('일치')
	}else{
		$("#pw1").val("") //지우기
		$("#pw2").val("")
		$("#pw2").focus()
		document.getElementById("label").innerHTML = "<span style='color:red;'>불일치</span>"
	}
}
</script>
</head>
<body>
	
	<c:import url="../default/header.jsp"/>
	<div class="wrap content">
		<form id="fo" action="register" method="post">
			<table border="1">
				<tr>
					<td>
						<input type="text" id='id' name="id" placeholder="input id">(*필수 항목)<br>
						<input type="text" id="pw1" name="pw" placeholder="input pass"><br>
						<input type="text" id="pw2" onchange="pwdchk()">
						<label id="label"></label>
						<br>
						
						<input type="text" readonly id="addr1" name="addr" >
						<input type="text" id="addr2">
						<input type="text" id="addr3"><br>
						<button type="button" onclick="daumPost()">
							주소찾기
						</button>
						<hr>
						<input type="button" onclick="register()" value="register"><br>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
	
</body>
</html>