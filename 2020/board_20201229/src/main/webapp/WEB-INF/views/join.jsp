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
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<style>
		.form-control{
			margin-top:15px;
		}
	</style>
	<script>
		$(document).ready(function(){
			var emailCheck=false;
			
			$("#form").submit(function(){
				
				if(!emailCheck){
					window.alert("이메일 중복확인을 해주세요")
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
			
			$("#email-check").on("click",function(){
				var email=$("[name='email']").val()
				console.log(email)
				$.post("${pageContext.request.contextPath}/checkemail.do",
						{email:email},
						function(result){
							console.log(result)
							if(result.result.success){
								emailCheck=true
							}
							
							window.alert(result.result.message)
						})
			})
		})
		
	</script>
	<style>
		#email-check{
			margin-top:15px;
		}
	</style>
</head>
<body>
	<div class="container">
		<form action="join.do" method="post" id="form">
			<div class="row">
				<div class="col">
					<input class="form-control" type="text" name="email" placeholder="이메일을 입력해주세요">		
				</div>
				<div class="col">
					<button type="button" id="email-check" class="btn btn-primary" >이메일 중복확인</button>
				</div>
			</div>
			
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