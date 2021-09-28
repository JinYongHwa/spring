<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
</head>
<body>

	<form method="POST" action="modify_proc" class="container">
		<input type="hidden" value="${board.id }" name="id">
		<input type="text" value="${board.title }" name="title" class="form-control">
		<div class="writer">작성자 : ${board.email }</div>
		<textarea class="form-control mt-2" name="body">${board.body}</textarea>
		
		<div class="text-center mt-2">
			<input type="submit" value="수정" class="btn btn-primary">
			<a class="btn btn-primary" href="view?id=${board.id }">취소</a>
		</div>
	</form>

</body>
</html>