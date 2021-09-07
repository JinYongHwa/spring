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
	<div class="container">
		<table class="table">
			<thead>
				<th class="text-center" width="60px">No</th>
				<th class="text-center">제목</th>
				<th class="text-center" width="100px">조회수</th>
				<th class="text-center" width="200px">작성시간</th>
			</thead>
			<tbody>
				<c:forEach items="${boardList }" var="board" varStatus="status">
					<tr onClick="location.href='view?id=${board.id}'">
						<td class="text-center">${nav.startNum-status.index}</td>
						<td class="text-center">${board.title }</td>
						<td class="text-center">${board.viewCount }</td>
						<td class="text-center">${board.createDate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div class="text-center">
			
			<c:if test="${ nav.prev }">
				<a class="btn" href="list?page=${nav.prevPage }">&lt;</a>
			</c:if>
		
			<c:forEach items="${nav.navArr }" var="page">
				<a class="btn <c:if test="${ nav.page==page }">btn-primary</c:if>" href="list?page=${page}">${page}</a>
			</c:forEach>
			
			<c:if test="${nav.next }">
				<a class="btn" href="list?page=${nav.nextPage }">&gt;</a>
			</c:if>
			
		</div>
		
		<div class="right">
			<a class="btn btn-primary" href="write">글 작성</a>
		</div>
	</div>	
</body>
</html>