<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu"
	crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<style>
		.form-control{
			margin-top:15px;
		}
	</style>
	<script>
		$(document).ready(function(){
			$("#form").submit(function(){
				var email=$("[name='email']").val()
				var emailReg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
				if(!emailReg.test(email)){
					window.alert("이메일을 확인해주세요")
					$("[name='email']").focus()
					return false;
				}
				
				var name=$("[name='name']").val()
				if(name.lengh==0){
					window.alert("이름을 입력해주세요")
					$("[name='name']").focus()
					return false;
				}
				var password=$("[name='password']").val()
				var passRule = /^[A-Za-z0-9]{6,12}$/;
				if(!passRule.test(password)){
					window.alert("패스워드는 6~12자이내로 숫자,영문자로 입력해주세요")
					$("[name='password']").focus()
					return false;
				}

				
				return true
			})	
		})
		
	</script>
</head>
<body>
	<div class="container">
		<form action="join.do" method="post" id="form">
			<input class="form-control" type="text" name="email" placeholder="이메일을 입력해주세요">
			<input class="form-control" type="text" name="name" placeholder="이름을 입력해주세요">
			<input class="form-control" type="password" name="password" placeholder="패스워드를 입력해주세요">
			<input class="form-control" type="submit" value="회원가입">
		</form>
	</div>
	<c:if test="${msg!=null }">
		<script>
			window.alert("${msg}")
		</script>
	</c:if>
	
</body>
</html>