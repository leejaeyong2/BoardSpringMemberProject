<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script  src="https://code.jquery.com/jquery-3.4.1.js"></script>
<title>Insert title here</title>
</head>
<body>
	회원가입
	<form action="joinProc" method="post" enctype="multipart/form-data">
		프로필 사진<br>
		<input type=file id="image" name="image" accept=".png, .PNG, .jpg, .jpeg, .JPG, .JPEG"><br>
		<input type=text placeholder="아이디" id="id" name="id" required><span id="idChk"></span><br>
<!-- 			<div id="alertIdAvailable">사용가능한 아이디입니다.</div> -->
<!-- 			<div id="alertIdNotAvailable">이미 존재하는 아이디입니다.</div> -->
		<input type=password placeholder="비밀번호" id="pwd1" name="pw" required><br>
		<input type=password placeholder="비밀번호 확인" id="pwd2" name="pw_chk" required><br>
			<div class="alert alert-success" id="alert-success">비밀번호가 일치합니다.</div>
			<div class="alert alert-danger" id="alert-danger">비밀번호가 일치하지 않습니다.</div>

		<input type=email placeholder="이메일 주소" name="email" required><br>
		<input type="phone" placeholder="전화번호" name="phone" required><br>
		<input type=submit value="가입">
	</form>
	<script>
	  $(function(){
		    $("#alertIdAvailable").hide();
		    $("#alertIdNotAvailable").hide();
		  	$("#id").on("input",function(){
		  		$.ajax({
		  			url:"idChk.ajax",
		  			data:{loginId:$("#id").val()}
		  		}).done(function(resp){
		  			if(resp=="exist"){
		  				$("#idChk").text("이미 존재하는 아이디입니다.");
// 		  			  $("#alertIdNotAvailable").show();
// 		  			  $("#alertIdAvailable").hide();

		  			}else{
		  				$("#idChk").text("사용 가능한 아이디입니다.");
// 		  			 $("#alertIdAvailable").show();
// 		  			 $("#alertIdNotAvailable").hide();

		  			}
		  		})
		  	})
		  
	        $("#alert-success").hide();
	        $("#alert-danger").hide();
	        $("input").keyup(function(){
	            var pwd1=$("#pwd1").val();
	            var pwd2=$("#pwd2").val();
	            if(pwd1 != "" || pwd2 != ""){
	                if(pwd1 == pwd2){
	                    $("#alert-success").show();
	                    $("#alert-danger").hide();
	                    $("#submit").removeAttr("disabled");
	                }else{
	                    $("#alert-success").hide();
	                    $("#alert-danger").show();
	                    $("#submit").attr("disabled", "disabled");
	                }    
	            }
	        });
	    });

	</script>
</body>
</html>