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
		<div v-if="board">
			<div class="container">
				<table class="table">
					<tr>
						<th width="120px">제목</th>
						<td>{{board.title}}</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>{{board.email}}</td>
					</tr>
					<tr>
						<th>조회수</th>
						<td>{{board.viewCount}}</td>
					</tr>
					<tr>
						<td colspan="2">
							{{board.bodyBr}}
						</td>
					</tr>
				</table>
				<div class="text-center" v-if="user!=null && user.id==board.userId">
					<div class="btn btn-primary" @click="modify">수정</div>
					<div class="btn btn-danger" @click="remove">삭제</div>
				</div>
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
					board:null,
					user:null
				}
			},
			mounted(){
				var param=getUrlParams()
				axios.post("view",{
					id:param.id
				}).then(result=>{
					this.board=result.data.board
				})
				
				
				axios.post("userinfo")
				.then(result=>{
					if(result.data.result){
						this.user=result.data.user
					}
				})
				
			},
			methods:{
				modify(){
					location.href="modify?id="+this.board.id
				},
				remove(){
					if(!window.confirm("정말 삭제하시겠습니까?")){
						return
					}
					axios.post("remove",{id:this.board.id})
					.then(result=>{
						if(result.data.result){	//삭제가 성공했을때
							location.href="list"
						}
						else{	// 삭제 실패시
							window.alert(result.data.message)
						}
					})
				}
			}
		})
	</script>
</body>
</html>