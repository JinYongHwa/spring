<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<style>
	.title{
		font-size:25px;
		font-weight:bold;
	}
	.date{
		text-align:right;
		color: #555;
	}
	.body{
		background:#eee;
		min-height:400px;
		padding:20px;
		margin-top:20px;
	}

</style>
</head>
<body>
	<div class="container">
		<div class="title">${board.title }</div>
		<div class="date">${board.createDate }</div>
		<div class="body">${board.bodyBr }</div>
		
		<div class="text-center mt-2">
			<a class="btn btn-danger" href="delete?id=${board.id}">삭제</a>
			<a href="list" class="btn btn-primary">목록</a>
			<a class="btn btn-primary" href="modify?id=${board.id}">수정</a>
		</div>
		
	</div>	
</body>
</html>