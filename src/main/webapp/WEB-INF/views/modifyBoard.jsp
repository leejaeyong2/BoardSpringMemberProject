<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>
<style>
	#content{
		margin:auto;
		margin-top:20px;
		width:600px;
	}
	
</style>
</head>
<body>
	<form action="modifyBoard" id="form" method="post"
		enctype="multipart/form-data" accept-charset="UTF-8">
		<div id="content">
			<input type="hidden" name="bNo" value="${dto.bNo }">
			<div id="title"><input type="text" name="title" value="${dto.title }" style="width:300px"></div>
			<div id="summernote" contenteditable="true"></div>
			<input id="myContent" type="hidden" name="contents">
			<input type=button value="완료" id="send">
		</div>
	</form>
	<script>
			$("#send").on("click",function(){
			var contents = $(".note-editable").html();
			$("#myContent").val(contents);
			$("#form").submit();
		})
	
		$('#summernote').summernote({
			placeholder : "${dto.contents}",
			tabsize : 2,
			height : 500,

			callbacks : {
				onImageUpload : function(files, editor, welEditable) {
					for (var i = files.length - 1; i >= 0; i--) {
						sendFile(files[i], this);
					}
				},

				onMediaDelete : function(target) {
					deleteFile(target[0].src);
				}
			}
		});

		function sendFile(file, editor) {
			var data = new FormData();
			data.append("file", file);
			$.ajax({
				url : "uploadImage.board",
				data : data,
				type : "POST",
				cache : false,
				contentType : false,
				enctype : "multipart/form-data",
				processData : false
			}).done(function(resp) {
				$(".note-editable").append("<img src='"+resp+"'>");
			})
		}

		function deleteFile(src) {
			if (src == "photo_image/foryou.jpg" || src == "photo_image/ka.png"
					|| src == "photo_image/fa.png"
					|| src == "photo_image/kk.png") {
			} else if (src == null) {
			} else {
				$.ajax({
					data : {
						src : src
					},
					type : "POST",
					url : "deleteImage.board",
					cache : false,
					success : function(resp) {
						if (resp == "true") {
							console.log("정상 삭제");
						} else {
							console.log("삭제 실패");
						}
					}
				});
			}
		}
	</script>
</body>
</html>