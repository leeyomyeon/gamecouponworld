<template>
  <div>
    <LoginForm></LoginForm>
    <div class="login-bar"></div>
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
  margin: 30px;
}
</style>
