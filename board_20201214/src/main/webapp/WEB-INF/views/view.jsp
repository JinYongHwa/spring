<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${ board.title }</title>
	<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu"
	crossorigin="anonymous">
	<style>
		.title{
			font-size:20px;
			font-weight:bold;
		}
		.contents{
			border-top :1px solid #ddd;
			border-bottom:1px solid #ddd;
			padding:20px;
			min-height:400px;
		}
	</style>
</head>
<body>
	<div class="container">
		<div class="title">${board.title }</div>
		<div class="text-right">작성자 : ${board.writer}</div>
		<div class="text-right">조회수 : ${board.viewCount}</div>
		<div class="text-right">작성시간 : ${board.formattedWriteDate}</div>
		<div class="contents">
			${board.formattedText }
		</div>
		
		<div class="text-center">
			<a href="${ pageContext.request.contextPath }/list">목록</a>
			<a href="${pageContext.request.contextPath }/modify?id=${board.id}">수정</a>
			<a href="${pageContext.request.contextPath }/remove.do?id=${board.id}">삭제</a>
		</div>
		
	</div>
	
	
</body>
</html>