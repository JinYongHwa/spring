<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
	.login-form{
		width:600px;
		max-width:100%;
		margin:100px auto;
		box-shadow:3px 5px 10px #ddd;
		padding:20px;
		
	}
	.login-form .form-control{
		margin-bottom:10px;
	}
	.header{
		text-align:center;
		font-size:20px;
		margin-bottom:20px;
	 }
	
</style>
<script type="text/javascript">
	 $(document).ready(function(){
		 $(".login-form").submit(function(){
			 
			 if($("[name='id']").val()==''){
				 window.alert("아이디를 입력해주세요")
				 $("[name='id']").focus()
				 return false
			 }
			 if($("[name='name']").val()==''){
				 window.alert("이름을 입력해주세요")
				 $("[name='name']").focus()
				 return false
			 }
			 if($(".password").val()==''){
				 window.alert("패스워드를 입력해주세요")
				  $(".password]").focus()
				 return false
			 }
			 if($(".password").val()!=$(".password-confirm").val()){
				 $(".password-confirm").val('')
				 $(".password-confirm").focus()
				 window.alert("패스워드를 확인해주세요")
				 return false
			 }
			 
			 return true
		 })
	 })
</script>
</head>
<body>


<form class="input-group login-form" method="POST" action="join_proc">
	<div class="header">회원가입</div>
	<input  class="form-control" name="id" type="text"  placeholder="아이디">
	<div>
		<c:out value="${message }"></c:out>
	</div>
	<input  class="form-control" name="name" type="text"  placeholder="이름">
	<input  class="form-control password" name="password" type="password"  placeholder="패스워드">
	<input  class="form-control password-confirm" type="password"  placeholder="패스워드 확인">
	<input type="submit" class="form-control" value="회원가입">
	<input type="button" class="form-control" value="취소" onClick="history.go(-1)">
	
</form>

</body>
</html>