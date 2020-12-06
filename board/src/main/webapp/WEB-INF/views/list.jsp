<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<link href="${pageContext.request.contextPath}/resources/common.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">

</head>
<body>

	<div class="container">
		<table>
			<colgroup>
				<col width="80px">
				<col>
				<col width="150px">
			</colgroup>
			<tr>
				<th width="80px" class="text-center">글번호</th>
				<th class="text-center">제목</th>
				<th width="100px" class="text-center">작성자</th>
				<th width="150px" class="text-center">작성일</th>
			</tr>

			<c:forEach items="${list}" var="item" varStatus="status">
				<tr>
					<td class="text-center"><c:out
							value="${nav.startNum-status.index }"></c:out></td>
					<td class="text-center">
					<a href="${ pageContext.request.contextPath }/board?id=${item.id}&page=${currentPage}">
					<c:out value="${item.title }"></c:out>
					</a>
					
					</td>
					<td class="text-center"><c:out value="${item.writer}"></c:out></td>
					<td class="text-center"><c:out value="${item.formattedDate }"></c:out></td>
				</tr>
			</c:forEach>

		</table>

		<div class="navigator text-center">
			<c:if test="${nav.prev }">
				<a href="${pageContext.request.contextPath }/?page=${nav.prevPage}">&lt;</a>
			</c:if>
			<c:forEach items="${nav.navArr}" var="page">
				<a class="<c:if test="${ currentPage==page }">active</c:if>"
					href="${pageContext.request.contextPath}/?page=${page}"> <c:out
						value="${page }"></c:out>
				</a>
			</c:forEach>
			<c:if test="${nav.next }">
				<a href="${pageContext.request.contextPath }/?page=${nav.nextPage}">&gt;</a>
			</c:if>

		</div>



		<div class="text-right">
			<a href="${pageContext.request.contextPath}/write">글쓰기</a>
		</div>

	</div>



</body>
</html>
