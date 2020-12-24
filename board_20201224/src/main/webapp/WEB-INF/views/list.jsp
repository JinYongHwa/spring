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
<style type="text/css">
table.board {
	width: 100%;
}

table.board td, th {
	border-bottom: 1px solid #ddd;
}

table.board th {
	text-align: center;
}

.navigator {
	margin-top: 20px;
	text-align: center;
}

.navigator a {
	display: inline-block;
	width: 30px;
	height: 30px;
	border: 1px solid #ddd;
	text-align: center;
	line-height: 30px;
}

.navigator a.active {
	background: #aaa;
	color: white;
	text-decoration: none;
}
</style>
</head>
<body>
	<div class="container">
		<div class="text-right">
			<c:if test="${user!=null }">
			${user.name }님 안녕하세요
		</c:if>
		</div>
	</div>


	<div class="container">
		<table class="board">
			<thead>
				<tr>
					<th width="80px">글번호</th>
					<th>제목</th>
					<th width="120px">작성자</th>
					<th width="100px">조회수</th>
					<th width="200px">작성시간</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ list }" var="board" varStatus="status">
					<tr>
						<td class="text-center">${  nav.startNum - status.index }</td>
						<td><a
							href="${pageContext.request.contextPath }/view?id=${board.id}&page=${nav.page}">
								${board.title } </a></td>
						<td class="text-center">${board.writer }</td>
						<td class="text-center">${board.viewCount }</td>
						<td class="text-center">${board.formattedWriteDate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="navigator">

			<c:if test="${nav.prev }">
				<a
					href="${pageContext.request.contextPath }/list?page=${nav.prevPage}">&lt;</a>
			</c:if>
			<c:forEach items="${nav.navArr }" var="page">
				<c:if test="${page==nav.page }">
					<a class="active">${page }</a>
				</c:if>
				<c:if test="${page!=nav.page }">
					<a href="${pageContext.request.contextPath }/list?page=${page}">${page }</a>
				</c:if>

			</c:forEach>
			<c:if test="${nav.next }">
				<a
					href="${pageContext.request.contextPath }/list?page=${nav.nextPage}">&gt;</a>
			</c:if>
		</div>
		
		<c:if test="${ user!=null }">
			<div class="text-right">
				<a href="${pageContext.request.contextPath }/write">글쓰기</a>
			</div>
		</c:if>
		<c:if test="${ user==null }">
			<div class="text-right">
				<a href="${pageContext.request.contextPath }/login">로그인</a>
			</div>
		</c:if>
		
	</div>
</body>
</html>