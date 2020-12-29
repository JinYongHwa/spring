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
</head>
<body>
	<div id="app">
		<v-app>
			<div>
				<v-text-field v-model="form.title" label="글제목"></v-text-field>
			</div>
			<div>
				<v-textarea v-model="form.text" label="내용"></v-textarea>
			</div>
			<div class="text-center">
				<v-btn color="primary" @click="write">글작성</v-btn>
			</div>
			
		</v-app>
	</div>
	<script>
		new Vue({
			el:"#app",
			vuetify:new Vuetify(),
			data:{
				form:{
					title:"",
					text:"",
				}
			},
			methods:{
				write(){
					axios.post("${pageContext.request.contextPath }/mobile/write.do",this.form)
					.then(result=>{
						
						if(result.data.result){
							location.replace("${pageContext.request.contextPath }/mobile/view?id="+result.data.board.id)
						}
						else{
							location.replace("${pageContext.request.contextPath }/mobile/login")
						}
					})
				}
			}
		})
	
	</script>
</body>
</html>