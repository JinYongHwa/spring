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
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	
</head>
<body>
	<div id="app">
		<div class="container">
			<input v-model="board.title" type="text" class="form-control mt-2" placeholder="제목">
			<div id="editor" ref="editor"></div>
			
			<textarea v-model="board.body" class="form-control mt-2" placeholder="내용"></textarea>
			<div class="text-center mt-2">
				<div class="btn btn-primary" @click="write">작성</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var app=new Vue({
			el:"#app",
			data(){
				return {
					board:{
						title:"",
						body:""
					}
				}
			},
			mounted(){
				 
				axios.post("userinfo")
				.then(result=>{
					if(!result.data.result){  //로그인 안되있을경우
						location.href="login"
					}
				})
			},
			methods:{
				write(){
					
					if(this.board.title==""){
						window.alert("제목을 입력해주세요")
						return
					}
					if(this.board.body==""){
						window.alert("내용을 입력해주세요")
					}
					axios.post("board/write",this.board)
					.then(result=>{
						if(result.data.result){
							location.href="list"
						}
					})
				}
			}
		})
	</script>
</body>
</html>