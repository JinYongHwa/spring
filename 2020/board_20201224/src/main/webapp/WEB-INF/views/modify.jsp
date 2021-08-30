<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu"
	crossorigin="anonymous">

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
		.attachfile-item{
			margin-top:5px;
			margin-bottom:5px;
		}
		.file-name{
			display:inline-block;
			margin-right:20px;
		
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".delete-btn").on("click",function(){
				$(this).parent().remove();
			})
		})
	</script>
</head>
<body>

	<div class="container">
		<form id="form" action="modify.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${board.id }"> 
			<input type="hidden" name="page" value="${query.page }"> 
			<input  name="title" class="form-control" placeholder="제목" value="${board.title }"> 
			<div class="writer-container">
				<div class="header ">작성자</div>
				<div class="body ">${user.name }</div>
			</div>
			<input type="hidden" value="${user.id }" name="userId">
			<!--
				<input name="writer" class="form-control" placeholder="작성자" value="${board.writer }">  
			-->
			<textarea id="summernote" name="text" class="form-control" placeholder="글내용">
				${board.text }
			</textarea>
			<input name="files" type="file" class="form-control" label="첨부파일" multiple>
			
			<div class="attachfile-list">
				<c:forEach items="${board.attachFiles }" var="attachFile">
					<div class="attachfile-item" >
						<div class="file-name">
							${attachFile.originalFileName }
						</div>
						<button type="button" class="btn delete-btn"  id="${attachFile.id }">삭제</button>
						<input type="hidden" name="attachIds" value="${attachFile.id }">
					</div>
					
				</c:forEach>
			
			</div>
			
			<input type="submit" value="수정" class="form-control">
		</form>
	</div>

</body>
</html>