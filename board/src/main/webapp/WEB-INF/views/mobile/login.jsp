<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
</head>
<body>
	<div id="app">
		<div class="container">
			<input v-model="form.email" type="text" placeholder="아이디" class="form-control" >
			<input v-model="form.password" type="password" placeholder="비밀번호" class="form-control mt-2">
			<div class="text-center">
				<div class="btn btn-primary mt-2" @click="login">로그인</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		var app=new Vue({
			el:"#app",
			data(){
				return {
					form:{
						email:"",
						password:""
					}
				}
			},
			methods:{
				login(){
					if(this.form.email==""){
						window.alert("이메일을 입력해주세요")
						return
					}
					if(this.form.password==""){
						window.alert("비밀번호를 입력해주세요")
						return
					}
					axios.post("login_proc",this.form)
					.then(result=>{
						if(result.data.result){	//로그인 됬을때
							location.href="list"
						}
						else{		//로그인 실패시
							window.alert(result.data.message)
						}
					})
				}
			}
		})
	
	</script>
	
	
	
	
</body>
</html>