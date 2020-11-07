<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
	.login-form{
		width:600px;
		max-width:100%;
		margin:100px auto;
		box-shadow:0px 5px 5px #ddd;
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
</head>
<body>

<form class="input-group login-form" action="login_proc" method="POST">
<div class="header">회원가입</div>
	<input  class="form-control" name="id" type="text"  placeholder="아이디">
	<input  class="form-control" name="password" type="password"  placeholder="패스워드">
	<input type="submit" class="form-control" value="로그인">
	<input type="button" class="form-control" value="회원가입" onClick="location.href='join'">
	
</form>

</body>
</html>