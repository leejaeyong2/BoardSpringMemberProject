<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script  src="https://code.jquery.com/jquery-3.4.1.js"></script>
<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<c:choose>
	<c:when test="${sessionScope.loginId != null }">
		<input type=button value="로그아웃" id="logout">
		<input type=button value="마이페이지" id="toMyPage">
		<input type=button value="게시판" id="toBoard">
		<input type=button value="채팅방" id="toChat">
	</c:when>
	<c:otherwise>
		<form action="loginProc" method="post">
			<input type=text placeholder="아이디" id="id" name="id">
			<input type=password placeholder="비밀번호" id="pw" name="pw">
			<input type=submit value="로그인" id="toLogin">
		</form>
		<input type=button value="회원가입" id="toJoin">
	</c:otherwise>
</c:choose>	
<script>
	$("#toChat").on("click",function(){
		$(location).attr("href","toWebChat");
	})
	$("#toBoard").on("click",function(){
		$(location).attr("href","toBoard?currentPage=1");
	})
	$("#toMyPage").on("click",function(){
		$(location).attr("href","myPage");
	})
	$("#toJoin").on("click", function(){
		$(location).attr("href","joinForm");
	})
	$("#logout").on("click",function(){
		$(location).attr("href","logout");
	})
</script>
</body>
</html>
