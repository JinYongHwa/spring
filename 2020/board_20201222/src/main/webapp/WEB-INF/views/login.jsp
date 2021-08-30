<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<form action="" >
			<input class="form-control" type="text" name="email" placeholder="이메일을 입력하세요">
			<input class="form-control" type="password" name="password" placeholder="패스워드를 입력하세요">
			<input class="form-control" type="submit" value="로그인">
			<a class="form-control" href="${ pageContext.request.contextPath }/join">회원가입</a>
		</form>
	</div>
</body>
</html>