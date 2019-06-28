<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Insert title here</title>
<style>
#wrapper {
	
	width: 600px;
	margin: auto;
}

	div{
		
		box-sizing: border-box;
	}
	.chatBox{
		position:fixed;
		bottom:0;
		right:0;
		width:200px;
		height:300px;
		margin:0 auto;
		border:1px solid lightgrey;
	}
	.contents{
		width:100%;
		height:85%;
		overflow-y:auto;	
	}
	.control{
		width:100%;
		height:15%;
	}
	.control>input[type=text]{
		height:100%;
		width:70%;
		box-sizing:border-box;
	}
	.control>input[type=button]{
		height:100%;
		width:25%;
		box-sizing:border-box;
	}
	.myMsg{
		text-align:right;
	}
	#forBtns{
		text-align:right;
	}
</style>
</head>
<body>
	<div id="wrapper">
		<table class="table">
			<thead>
				<tr>
					<th scope="col"></th>
					<th scope="col">제목</th>
					<th scope="col">내용</th>
					<th scope="col">작성자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${list}">
					<tr>
						<th scope="row">${ dto.bNo }</th>
						<td><a href="readBoard?bNo=${dto.bNo }">${ dto.title }</a></td>
						<td>${ dto.contents }</td>
						<td>${ dto.writer }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!--페이지네비게이터 -->
		<div class="row  p-0 m-0" id="pageNavi">
			<div class="col-12 d-flex justify-content-center">
				<nav aria-label="Page navigation example">
				<ul class="pagination pagination-sm">${pageNavi }
				</ul>
				</nav>
			</div>
		</div>
		<div id="forBtns"><input type=button value="메인" id="btnToMain"><input type=button value="글작성" id="btnWrite"></div>
	</div>
<!-- 채팅창 -->
	<div class="chatBox">
		<div class="contents"></div>
		<div class="control">
			<input type=text id="input"> <input type=button id="send"
				value="send">
		</div>
	</div>
	<script>
		$("#btnToMain").on("click",function(){
			location.href="/";
		})
		$("#btnWrite").on("click",function(){
			location.href="toWrite";
		})
		// 채팅창
		var socket = new WebSocket("ws://192.168.60.17/chat");
		// 서버로부터 메세지가 들어왔을 때 아래를 실행하겠다
		socket.onmessage = function(evt){
			$(".contents").append("<p>"+evt.data+"</p>");
			$(".contents").scrollTop($(".contents")[0].scrollHeight);
		}
		
		//서버로 메세지 보내는 경우
		$("#send").on("click",function(){
			var msg = $("#input").val();
			$(".contents").append("<p class='myMsg'>나:"+msg+"</p>");
			$(".contents").scrollTop($(".contents")[0].scrollHeight);
			$("#input").val("");
			$("#input").focus();
			socket.send(msg);	// @OnMessage 로 간다.
		})
	</script>
</body>
</html>