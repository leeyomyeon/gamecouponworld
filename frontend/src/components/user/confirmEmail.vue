<template>
  <div class="box">
    <p class="sub-title">이메일 인증</p>
    <div clss="container">
      <div class="item">
        <div class="input-box">
          <input
            type="email"
            id="email"
            v-model="email"
            placeholder=" "
            spellcheck="false"
            autocomplete="off"
          />
          <label for="email">이메일</label>
        </div>
        <p v-if="!isEmailValid && email">
          이메일 형식이 아닙니다.
        </p>
        <p v-if="!isEmailLenValid">
          email 길이 초과하였습니다.
        </p>
        <p v-if="emailCheck">
          이미 가입된 이메일입니다.
        </p>
      </div>
      <button
        v-if="isLoding"
        style="width: 40%; height:2em; padding:0;"
        class="content-center"
      >
        <div class="loding"></div>
      </button>
      <button
        v-else
        :disabled="!isEmailLenValid || !isEmailValid"
        @click="submit"
        style="width: 40%; height:2em; padding:0;"
      >
        {{ logSend }}
      </button>
      <span v-if="buttonCheck && !emailCheck" class="alert-email"
        >인증메일 전송완료</span
      >

      <div v-if="buttonCheck && !emailCheck" class="item smooth">
        <div style="width:70%;" class="input-box">
          <input id="code" v-model="code" placeholder=" " autocomplete="off" />
          <label for="code">인증번호</label>
        </div>
        <button
          style="width: 20%; margin: 0.4em; padding: 0;"
          @click="move"
          :disabled="!code"
        >
          확인
        </button>
        <p v-if="logMessage">{{ logMessage }}</p>
      </div>
    </div>
  </div>
</template>
<script>
import { confirmEmail } from '@/api/auth'
import { validateEmail } from '@/utils/validation'
export default {
  data() {
    return {
      email: '',
      code: '',
      data: '',
      // check
      buttonCheck: false,
      emailCheck: false,
      isLoding: false,
      // message
      logSend: '인증번호받기',
      logMessage: '',
    }
  },
  computed: {
    isEmailLenValid() {
      return this.email.length <= 30
    },
    isEmailValid() {
      return validateEmail(this.email)
    },
  },
  methods: {
    async submit() {
      try {
        const userData = this.email
        this.buttonCheck = false
        this.isLoding = true
        const temp = await confirmEmail(userData)
        this.isLoding = false
        this.buttonCheck = true
        this.data = temp.data
        console.log(temp)
        if (this.data == 1) this.emailCheck = true
        else {
          this.emailCheck = false
          this.logSend = '인증번호 재전송'
        }
      } catch (error) {
        alert(error)
      }
    },
    move() {
      if (this.data == this.code && this.data !== null) {
        this.logMessage = ''
        this.$router.push('/account/signup?email=' + this.email)
      } else {
        this.logMessage = '인증번호가 일치하지 않습니다.'
      }
    },
  },
}
</script>

<style scoped src="../css/user/default.css"></style>
<style scoped src="../css/user/confirm.css"></style>
<style scoped></style>
