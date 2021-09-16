<template>
    <div>
       <div v-if="board">{{board.title}}</div> 
    </div>
</template>

<script>

export default {
    data(){
        return {
            board:null
        }
    },
    
    mounted(){
        var id=this.$route.params.id
        this.$axios.post("/mobile/view",{id:id})
        .then(result=>{
            this.board=result.data.board
            console.log(this.board)
            
            
        })
    }
}
</script>