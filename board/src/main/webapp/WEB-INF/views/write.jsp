<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/common.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
</head>
<body>

	<div class="container">
		<form action="write.do" method="post">
			<input class="form-control" name="title" placeholder="제목">
			<input class="form-control mt-2" name="writer" placeholder="작성자">
			<textarea class="form-control mt-2" height="500px" name="text" placeholder="내용"></textarea>
			<div class="text-center mt-2">
			
				<input type="submit" class="btn btn-primary" value="작성">
			</div>
		</form>


	</div>


</body>
</html>