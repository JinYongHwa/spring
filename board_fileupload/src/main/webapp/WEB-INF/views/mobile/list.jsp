<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 상용버전, 속도와 용량이 최적화됨. -->
<script src="https://cdn.jsdelivr.net/npm/vue"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="https://momentjs.com/downloads/moment.js"></script>
</head>
<body>
	
	<div id="app">
		<table class="board">
			
				<tr>
					<th width="80px">글번호</th>
					<th>제목</th>
					<th width="120px">작성자</th>
					<th width="100px">조회수</th>
					<th width="200px">작성시간</th>	
				</tr>
			<tbody>
				<tr v-for="board in list">
				<td>{{board.id}}</td>
				<td>{{board.title}}</td>
				<td>{{board.writer}}</td>
				<td>{{board.viewCount}}</td>
				<td>{{board.writeDate|date}}</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	
	<script>
	 new Vue({
		  el: '#app',
		  data: {
		    list: []
		  },
		  filters:{
			date(input){
				return moment(input).format("YYYY-MM-DD HH:mm")
			}  
		  },
		  mounted(){
			  axios.post("${pageContext.request.contextPath}/api/v1/list")
			  .then(result=>{
				  this.list=result.data.list
				  console.log(result.data)
			  })
		  },
		  
		})
	</script>
</body>
</html>