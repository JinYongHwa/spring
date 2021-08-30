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
	<style>
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
	</style>
</head>
<body>
	<div id="app">
		<table>
			<thead>
				<tr>
					<th>글번호</th>
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
			<div class="page-item" 
			v-for="page in nav.navArr" 
			:class="{active:page==nav.page}"
			@click="clickPage(page)" >
				{{page}}
			</div>
		</div>
		
	</div>
	
	<script>
		new Vue({
		  el: '#app',
		  data: {
		    message: '안녕하세요 Vue!',
		    list:[],
		    nav:null
		  },
		  mounted(){
			  axios.post("${pageContext.request.contextPath }/mobile/list.do?page=1")
			  .then(result=>{
				  console.log(result)
				  this.list=result.data.list
				  this.nav=result.data.nav
			  })
		  },
		  methods:{
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
