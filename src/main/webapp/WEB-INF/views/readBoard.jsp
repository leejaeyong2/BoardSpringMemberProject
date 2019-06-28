<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div id="wrapper">
		<div id="title">제목: ${dto.title }</div>
		<div id="writer">작성자: ${dto.writer }</div>
		<div id="contents"><p>내용</p> ${dto.contents }</div>
	</div>
	<div><input type=button value="수정" id="modify"><input type=button value="삭제" id="delete"></div>
	<script>
		$("#modify").on("click",function(){
			location.href="toModify?bNo=${dto.bNo}";
		})
		$("#delete").on("click",function(){
			location.href="delete?bNo=${dto.bNo}";
		})
	</script>
</body>
</html>