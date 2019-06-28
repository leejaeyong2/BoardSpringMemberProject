<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<title>Web Chat</title>
<style>
	div{
		border:1px solid black;
		box-sizing: border-box;
	}
	.container{
		width:400px;
		height:500px;
		margin:0 auto;
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
		width:80%;
		box-sizing:border-box;
	}
	.control>input[type=button]{
		height:100%;
		width:18%;
		box-sizing:border-box;
	}
	.myMsg{
		text-align:right;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="contents">
			
		</div>
		<div class="control">
			<input type=text id="input">
			<input type=button id="send" value="send">
		</div>
	</div>
	
	<script>
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









