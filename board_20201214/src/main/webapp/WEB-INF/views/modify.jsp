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
</head>
<body>
	
	<div class="container">
		<form action="modify.do" method="post">
			<input type="hidden" name="id" value="${board.id }">
			<input type="hidden" name="page" value="${query.page }">
			<input name="title" class="form-control" placeholder="제목" value="${board.title }">
			<input name="writer" class="form-control" placeholder="작성자" value="${board.writer }">
			<textarea name="text" class="form-control" placeholder="글내용">
				${board.text }
			</textarea>
			<input type="submit" value="수정" class="form-control">
		</form>
	</div>

</body>
</html>