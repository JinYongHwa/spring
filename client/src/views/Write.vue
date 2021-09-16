<template>
    <div>
        <v-container>
            <v-text-field v-model="board.title" label="제목"></v-text-field>
            <v-text-field v-if="user" v-model="user.email" label="작성자"></v-text-field>
            <v-textarea v-model="board.body" label="내용"></v-textarea>

            <div class="text-center">
                <v-btn color="primary" @click="write">글쓰기</v-btn>
            </div>
        </v-container>
    </div>
</template>
<script>
export default {
    data(){
        return {
            user:null,
            board:{
                title:"",
                body:""
            }
        }
    },
    mounted(){
        this.$axios.post("/mobile/userinfo",null,{ withCredentials: true }).then(result=>{
            if(result.data.result){ //로그인됬을때
                this.user=result.data.user
            }
            else{       //로그인 안됬을때
                this.$router.push("/login")
            }
        })
    },
    methods:{
        write(){
            if(this.board.title==""){
                window.alert("제목을 작성해주세요")
                return 
            }
            if(this.board.body==""){
                window.alert("내용을 작성해주세요")
                return
            }
            this.$axios.post("/mobile/board/write",this.board).then(result=>{
                if(result.data.result){
                    this.$router.push("/view/"+result.data.boardId)
                }
            })
        }
    }
}
</script>