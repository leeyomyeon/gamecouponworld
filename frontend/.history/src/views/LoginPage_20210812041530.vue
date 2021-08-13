<template>
  <div>
    <LoginForm></LoginForm>
    <div class="login-bar"></div>
    <div class="join-box">
      <em @click="$router.push('signup')">회원가입</em>
    </div>
  </div>
</template>

<script>
import LoginForm from '@/components/LoginForm.vue'

export default {
  components: {
    LoginForm,
  },
}
</script>

<style>
.login-bar {
  border-bottom: 1px solid #dadada;
  margin-left: 30px;
  margin-right: 30px;
}
.join-box {
  margin-left: 30px;
  margin-right: 30px;
  display: flex;
  justify-content: flex-end;
}
em {
  font-size: small;
  color: #ababab;
  font-style: normal;
}
</style>
