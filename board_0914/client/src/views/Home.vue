<template>
  <div class="home">
    
    <table>
      <thead>
        <tr>
          <th>글번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>조회수</th>
          <th>게시일</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(board,index) in boardList" :key="index">
          <td>{{board.id}}</td>
          <td>{{board.title}}</td>
          <td>{{board.email}}</td>
          <td>{{board.viewCount}}</td>
          <td>{{board.createDate|dateFormat}}</td>
        </tr>
      </tbody>
    </table>
    <div v-if="nav">

      <a v-for="page in nav.navArr" :key="page" @click="movePage(page)">{{page}}</a>
    </div>
  </div>
</template>

<script>
import moment from "moment"

export default {
  name: 'Home',
  data(){
    return {
      boardList:[],
      nav:null
    }
  },
  mounted(){
    this.$axios.get("list")
    .then(result=>{
      console.log(result)
      this.boardList = result.data.boardList
      this.nav = result.data.nav

    })
  },
  filters:{
    dateFormat(input){
      var formattedDate=moment(input).format("YYYY-MM-DD")
      return formattedDate
    }
  },
  methods:{
    movePage(page){
      console.log(page)
    }
  }
  
}
</script>

<style scoped>
table{
  width:100%;
  border-collapse: collapse;
}
tr,td{
  border-bottom:1px solid;
  padding:5px;
}
</style>









