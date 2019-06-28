<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<title>Insert title here</title>
<style>

img{
	display:block;
	margin:auto;
	width:200px;
	border-radius:10px;
}
#wrapper{
	width:300px;
	margin:auto;
}
</style>
</head>
<body>
	<div id="wrapper">
		<img src="${dto.profilePath }">
		<div>ID : ${dto.id }</div>
		<div>Email : ${dto.email }</div>
		<div>Phone : ${dto.phone }</div>
		<div><input type="button" value="탈퇴하기" id="signOut"><input type=button value=이전페이지 id="goBack"></div>
		
	</div>
</body>
<script>
	$("#signOut").on("click",function(){
		location.href="signOut";
	})
	$("#goBack").on("click",function(){
		location.href="/";
	})
</script>
</html>