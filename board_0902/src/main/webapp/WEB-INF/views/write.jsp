<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<style>
	
</style>
</head>
<body>
	<form class="container" method="POST" action="write_proc">
		<input type="text" placeholder="글제목" class="form-control mt-4" name="title">
		<textarea class="form-control mt-2" placeholder="내용" name="body"></textarea>
		<button class="form-control mt-2">글작성</button>
	</form>
	
</body>
</html>