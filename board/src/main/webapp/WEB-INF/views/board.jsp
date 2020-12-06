<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.title }</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/resources/common.css"
	rel="stylesheet">
<style>
.title-container {
	font-size: 25px;
	border-bottom: 1px solid #ddd;
	margin-bottom: 10px;
	padding-bottom: 10px;
}

.writer-container {
	font-size: 15px;
	border-bottom: 1px solid #ddd;
	margin-bottom: 10px;
	padding-bottom: 10px;
}

.text-container {
	min-height: 500px;
	font-size: 18px;
	border-bottom: 1px solid #ddd;
	margin-bottom: 10px;
	padding-bottom: 10px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="title-container">${board.title }</div>
		<div class="writer-container text-right">작성자 : ${board.writer }</div>

		<div class="text-container">${board.html }</div>
		<div class="text-center">
			<a class="btn btn-primary" href="${pageContext.request.contextPath }/?page=${page}">목록</a>
			<a class="btn btn-primary" 
			href="${pageContext.request.contextPath }/modify?id=${board.id}&page=${page}">수정</a>
			<a class="btn btn-danger" 
			href="${pageContext.request.contextPath }/delete.do?id=${board.id}&page=${page}">삭제</a>
		</div>
	</div>


</body>
</html>