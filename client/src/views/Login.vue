<template>
    <div>
      <v-text-field v-model="form.email" label="이메일"></v-text-field>
      <v-text-field v-model="form.password" label="비밀번호" type="password"></v-text-field>
      <div class="text-center">
          <v-btn color="primary" @click="login">로그인</v-btn>
      </div>
    </div>
</template>
<script>
export default {
    data(){
        return {
            form:{
                email:"",
                password:""
            }
        }
    },
    methods:{
        login(){
           if(this.form.email==""){
               window.alert("이메일을 입력해주세요")
               return
           }
           if(this.form.password.length<5||this.form.password.length>50){
               window.alert("비밀번호는 5-50 이내로 입력해주세요",this.form.password)
               return
           }
           this.$axios.post("/mobile/login",this.form,{ withCreadentials: true }).then(result=>{
               console.log(result)
               if(result.data.result){
                   this.$router.push("/write")
               }
               else{
                   window.alert(result.data.message)
               }
           })
        }
    }
}
</script>