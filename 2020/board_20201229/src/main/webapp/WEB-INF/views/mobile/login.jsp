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
	      <v-layout column>
	      <div>
	      	<v-text-field ref="email" v-model="form.email" label="이메일"></v-text-field>
	      </div>
	      
	      <div>
	      	<v-text-field ref="password" type="password" v-model="form.password" label="패스워드"></v-text-field>
	      </div>
	      
	      <div class="text-center">
	      	<v-btn color="primary" @click="login">로그인</v-btn>
	      </div>
	  
	      </v-layout>
	    </v-app>
	  </div>
	
	<script>
	    new Vue({
	      el: '#app',
	      data:{
	    	  form:{
	    		  email:"",
	    		  password:""
	    	  }
	      },
	      methods:{
	    	  login(){
	    		  var email=this.form.email
	    		  
	    		  if(email.length==0){
	    			  this.$refs.email.focus()
	    			  window.alert("이메일을 입력해주세요")
	    			  return
	    		  }
	    		  
	    		  var password=this.form.password
	    		  if(password.length<4){
	    			  this.$refs.password.focus()
	    			  this.form.password=""
	    			  window.alert("패스워드를 4자이상입력해주세요")
	    			  return
	    		  }
	    		  axios.post("${pageContext.request.contextPath }/mobile/login.do",{
	    			  email:this.form.email,
	    			  password:this.form.password
	    		  })
	    		  .then(result=>{
	    			  console.log(result.data)
	    			  if(result.data.result=="fail"){
	    				  window.alert(result.data.message)
	    			  }
	    			  else if(result.data.result=="success"){
	    				  location.replace("${pageContext.request.contextPath }/mobile/list")
	    			  }
	    		  })
	    		  
	    	  }
	      },
	      vuetify: new Vuetify(),
	    })
	  </script>
</body>
</html>