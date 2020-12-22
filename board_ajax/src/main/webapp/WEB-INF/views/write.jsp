<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu"
	crossorigin="anonymous">
	<!-- include summernote css/js -->
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	
</head>
<body>
	<div class="container">
		<form id="form" action="write.do" method="post"  enctype="multipart/form-data">
			<input name="title" class="form-control m-2" placeholder="제목">
			<input name="writer" class="form-control " placeholder="작성자">
			<textarea id="summernote" class="mt-2" name="text"></textarea>
			<input type="file" label="첨부파일" name="files" multiple>
			<input type="submit" value="글작성" class="form-control">
			
			
		</form>
	</div>
	<script>
		$('#summernote').summernote({
			height:400
		});
		$("#form").submit(function(){
			if($("[name='title']").val().length==0){
				$("[name='title']").focus()
				window.alert("제목을 입력해주세요")
				return false
			}
			if($("[name='writer']").val().length==0){
				$("[name='writer']").focus()
				window.alert("작성자를 입력해주세요")
				return false
			}
			if($('#summernote').summernote('isEmpty')){
				$("#summernote").summernote("focus")
				window.alert("내용을 입력해주세요")
				return false
			}
			
			return true;
		})
	</script>

</body>
</html>