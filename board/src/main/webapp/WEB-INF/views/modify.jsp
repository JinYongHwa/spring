<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.title }</title>
<link href="${pageContext.request.contextPath}/resources/common.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
</head>
<body>
	<div class="container mt-4">
		<form action="modify.do" method="post">
			<input value="${board.id }" name="id" type="hidden">
			<input value="${page}" name="page" type="hidden">
			<input class="form-control" name="title" value="${board.title }"  placeholder="제목">
			<input class="form-control mt-2" name="writer" value="${board.writer }" placeholder="작성자">
			<textarea class="form-control mt-2" height="500px"  name="text" placeholder="내용"> ${board.text }</textarea>
			<div class="text-center mt-2">
			
				<input type="submit" class="btn btn-primary" value="수정">
			</div>
		</form>


	</div>
</body>
</html>