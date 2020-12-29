<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/@mdi/font@4.x/css/materialdesignicons.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
	<style>
		.text{
			border-top:1px solid #ddd;
			border-bottom:1px solid #ddd;
			min-height:300px;
			padding:20px;
		}
	</style>
</head>
<body>
	<div id="app">
		<v-app>
			
			<div v-if="board">
				<div class="title">
					{{board.title}}
				</div>
				<div class="date text-right">작성일 : {{board.formattedWriteDate}}</div>
				<div class="writer text-right">작성자 : {{board.writer}}</div>
				<div class="view-count text-right">조회수 : {{board.viewCount}}</div>
				<div class="text">
					{{board.text}}
				</div>
				<div class="text-center mt-2">
					<v-btn color="primary" @click="moveList()">목록</v-btn>
				</div>
			</div>
			
		</v-app>
	</div>
	<script>
		function getUrlParams() {
		    var params = {};
		    window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str, key, value) { params[key] = value; });
		    return params;
		}
		new Vue({
			el:"#app",
			vuetify:new Vuetify(),
//			data:{
//				board:null
//			},
			data(){
				return {
					board:null
				}
			},
			mounted(){
				var param=getUrlParams()
				console.log(param)
				axios.post("${pageContext.request.contextPath }/mobile/board.do",param)
				.then(result=>{
					this.board=result.data.board
					
				})
			},
			methods:{
				moveList(){
					location.replace("${pageContext.request.contextPath }/mobile/list")
				}
			}
		})
	
	</script>
</body>
</html>




