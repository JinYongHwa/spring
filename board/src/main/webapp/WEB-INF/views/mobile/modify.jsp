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
		<div class="container" v-if="board">
			<input v-model="board.title" type="text" class="form-control mt-2">
			<textarea v-model="board.body" class="form-control mt-2"></textarea>
			<div class="text-center mt-2">
				<div class="btn btn-primary" @click="modify">수정</div>
			</div>
		</div>
	</div>
	<script>
	function getUrlParams() {
	    var params = {};
	    window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str, key, value) { params[key] = value; });
	    return params;
	}
		var app=new Vue({
			el:"#app",
			data(){
				return {
					board:null
				}
			},
			mounted(){
				var param=getUrlParams()
				axios.post("view",{id:param.id})
				.then(result=>{
					this.board=result.data.board
				})
			},
			methods:{
				modify(){
					axios.post("modify",{
						id:this.board.id,
						title:this.board.title,
						body:this.board.body
					})
					.then(result=>{
						if(result.data.result){	//수정 성공한경우
							location.href="view?id="+this.board.id
						}
						else{	//수정 실패시
							window.alert(result.data.message)
						}
					})
				}
			}
		})
	</script>
</body>
</html>