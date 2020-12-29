<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

<!-- include libraries(jQuery, bootstrap) -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/form.js"></script>
	
	<style>
		.header{
			width:150px;
			display:inline-block;
		}
		.body{
			display:inline-block;	
		}
		.writer-container{
			margin-top:15px;
			margin-bottom:15px;
		}
	</style>
</head>
<body>
	<div class="container">
		<form id="form" action="write.do" method="post" enctype="multipart/form-data">
			<input name="title" class="form-control" placeholder="제목"> 
			<div class="writer-container">
				<div class="header ">작성자</div>
				<div class="body ">${user.name }</div>
			</div>
			<input type="hidden" name="userId" value="${user.id }">
			<!--
				<input name="writer" class="form-control" placeholder="작성자">  
			-->
			<textarea id="summernote" name="text" class="form-control"
				placeholder="글내용"></textarea>
			<input name="files" type="file" class="form-control" label="첨부파일" multiple>
			<input type="submit" value="글작성" class="form-control">
		</form>
	</div>

</body>
</html>