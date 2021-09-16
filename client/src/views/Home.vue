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

      <v-btn v-if="nav.start!=1" x-small depressed @click="movePage(1)">
        <v-icon size="12px">mdi-chevron-double-left</v-icon>
      </v-btn>

      <v-btn x-small depressed v-if="nav.prev" @click="movePage(nav.prevPage)">
        <v-icon size="12px">mdi-chevron-left</v-icon>
      </v-btn>
      <v-btn x-small depressed 
      color="white"
      :class="{primary:page==nav.page}" 
      v-for="page in nav.navArr" 
      :key="page" @click="movePage(page)">{{page}}</v-btn>

      <v-btn x-small depressed v-if="nav.next" @click="movePage(nav.nextPage)">
        <v-icon size="12px">mdi-chevron-right</v-icon>
      </v-btn>

      <v-btn v-if="nav.end!=nav.lastPage" x-small depressed @click="movePage(nav.lastPage)">
        <v-icon size="12px">mdi-chevron-double-right</v-icon>
      </v-btn>
    </div>
 
    <div class="text-left">
      <v-btn depressed color="primary" @click="moveWrite">글쓰기</v-btn>
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
    this.$axios.get("/mobile/list")
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
    moveWrite(){
      
    },
    movePage(page){
      this.$axios.get("/mobile/list?page="+page)
      .then(result=>{
        this.boardList = result.data.boardList
        this.nav = result.data.nav
      })
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









