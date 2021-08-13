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

<style scoped src="../../css/board/loginAdd.css"></style>
