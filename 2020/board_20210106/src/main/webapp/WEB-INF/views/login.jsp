<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<form action="login.do" method="post" >
			<input class="form-control" type="text" name="email" placeholder="이메일을 입력하세요">
			<input class="form-control" type="password" name="password" placeholder="패스워드를 입력하세요">
			<input class="form-control" type="submit" value="로그인">
			<a class="form-control" href="${ pageContext.request.contextPath }/join">회원가입</a>
			
		</form>
	</div>
	<c:if test="${msg!=null }">
		<script>
			window.alert("${msg}")
		</script>
	</c:if>
	
</body>
</html>