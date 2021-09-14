<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
	<c:if test="${ message!=null }">
	<script>
		window.alert("${message}")
	</script>
	</c:if>

	<form class="container" method="post" action="login_proc">
		<input class="form-control mt-4 email" type="text" name="email" placeholder="이메일을 입력해주세요">
		<input class="form-control mt-2 password" type="password" name="password" placeholder="패스워드를 입력해주세요">
		<div class="text-center mt-2">
			<input class="btn btn-primary" type="submit" value="로그인">
			<a class="btn btn-primary" href="join">회원가입</a>
		</div>
	</form>
	<script>
		$("form").submit(function(){
			var email=$(".email").val()
			if(email==""){
				window.alert("이메일을 입력해주세요")
				$(".email").focus()
				return false
			}
			var password=$(".password").val()
			if(password.length<=4||password.length>50){
				window.alert("비밀번호는 5-50자 이내로 작성해주세요")
				$(".password").focus()
				return false
			}
			return true
		})
	</script>
</body>
</html>








