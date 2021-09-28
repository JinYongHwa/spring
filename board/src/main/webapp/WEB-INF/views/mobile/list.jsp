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
			<table class="table">
				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>작성시간</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(board,key) in boardList" :key="key" @click="moveBoard(board)">
						<td>{{nav.startNum-key}}</td>
						<td>{{board.title}}</td>
						<td>{{board.email}}</td>
						<td>{{board.viewCount}}</td>
						<td>{{board.createDate}}</td>
					</tr>
				</tbody>
			</table>	
		</div>
		<div class="text-center" v-if="nav">
			<a class="btn" v-if="nav.prev" @click="changePage(nav.prevPage)">&lt;</a>
			
			<a class="btn" @click="changePage(page)" :class="{'btn-primary':page==nav.page}"
			 v-for="(page,key) in nav.navArr" :key="key">
				{{page}}
			</a>
			
			<a class="btn" v-if="nav.next" @click="changePage(nav.nextPage)">&gt;</a>
		</div>
		
		<div class="btn btn-primary" @click="write">글쓰기</div>
	
	</div>
	
	<script>
		var app=new Vue({
			el:"#app",
			data(){
				return {
					boardList:[],
					nav:null,
					currentPage:1
				}
			},
			mounted(){
				this.getBoardList()
			},
			methods:{
				moveBoard(board){
					location.href="view?id="+board.id
				},
				write(){
					axios.post("userinfo")
					.then(result=>{
						if(result.data.result){	//로그인 되있을경우
							location.href="write"
						}
						else{	//로그인 안되있을경우
							location.href="login"
						}
					})
				},
				changePage(page){
					this.currentPage=page
					this.getBoardList()
				},
				getBoardList(){
					 axios.get("list_proc?page="+this.currentPage)
					 .then(result=>{
						 this.boardList=result.data.boardList
						 this.nav=result.data.nav
					 })
				}
			}
			
		})
	</script>
	
</body>
</html>








