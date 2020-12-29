<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/@mdi/font@4.x/css/materialdesignicons.min.css" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
	<style>
		.navigator{
			text-align:center;
		}
		.page-item{
			cursor:pointer;
			display:inline-block;
			width:30px;
			height:30px;
			text-align:center;
			line-height:30px;
			border:1px solid #ddd;
			margin:5px;
		}
		.page-item.active{
			background:#ddd;
			color:white;
		}
		.board{
			width:100%;
			border-collapse:collapse;
		}
		.board th,td{
			border-bottom:1px solid #ddd;
			text-align:center;
		}
	</style>
</head>
<body>
	<div id="app">
	<v-app>
		<div class="text-right" v-if="!isLogin">
			<v-btn color="primary" @click="moveLogin">로그인</v-btn>
		</div>
		<div class="text-right" v-if="isLogin">
			{{userinfo.name}}님 안녕하세요
			<v-btn color="primary" @click="logout">로그아웃</v-btn>
		</div>

		<table class="board">
			<thead>
				<tr>
					<th width="80px">글번호</th>
					<th>제목</th>
					<th>작성자</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="board in list">
					<td>
						{{board.id}}
					</td>
					<td>
						{{board.title}}
					</td>
					<td>
						{{board.writer}}
					</td>
				</tr>
			</tbody>
		</table>
	
		
		<div class="navigator" v-if="nav!=null">
			<div v-if="nav.prev" class="page-item" @click="clickPage(nav.prevPage)">
				&lt;
			</div>
			<div class="page-item" 
			v-for="page in nav.navArr" 
			:class="{active:page==nav.page}"
			@click="clickPage(page)" >
				{{page}}
			</div>
			<div v-if="nav.next" class="page-item" @click="clickPage(nav.nextPage)">
				&gt;
			</div>
		</div>
		
		
		<div class="text-right">
			<v-btn color="primary" @click="moveWrite">글쓰기</v-btn>
		</div>
		
		<v-app>
	</div>
	
	<script>
		new Vue({
		  el: '#app',
		  vuetify: new Vuetify(),
		  data: {
		    message: '안녕하세요 Vue!',
		    list:[],
		    nav:null,
		    test:"",
		    isLogin:false,
		    userinfo:null
		  },
		  mounted(){
			  axios.post("${pageContext.request.contextPath }/mobile/list.do?page=1")
			  .then(result=>{
				  this.list=result.data.list
				  this.nav=result.data.nav
			  })
			  
			  this.getUserinfo()
		  },
		  methods:{
			  moveWrite(){
				location.replace("${pageContext.request.contextPath }/mobile/write")  
			  },
			  getUserinfo(){
				  axios.post("${pageContext.request.contextPath }/mobile/userinfo.do")
				  .then(result=>{
					  console.log(result.data)
					  this.isLogin=result.data.result
					  if(result.data.result){
						  this.userinfo=result.data.user
					  }
				  })
			  },
			  
			  logout(){
				var result=window.confirm("정말 로그아웃하시겠습니까?")
				if(!result){
					return
				}
				axios.post("${pageContext.request.contextPath }/mobile/logout.do")
				.then(result=>{
					if(result.data.result){
						this.getUserinfo()
					}
				})
			  },
			  moveLogin(){
				  location.replace("${pageContext.request.contextPath }/mobile/login")
			  },
			  clickPage(page){
				  axios.post("${pageContext.request.contextPath }/mobile/list.do?page="+page)
				  .then(result=>{
					 
					  this.list=result.data.list
					  this.nav=result.data.nav
				  })
			  }
		  }
		})
	</script>
</body>
</html>
