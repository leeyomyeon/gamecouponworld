<template>
  <div>
    <div class="login-box">
      <form @submit.prevent="submitForm">
        <p class="sub-title">로그인</p>
        <div class="item">
          <div class="input-box">
            <input
              id="userID"
              type="text"
              placeholder=" "
              autocomplete="off"
              v-model="userID"
              @focus="afterFocus('userID')"
              @keyup="isBlankVaild(userID, 'userID')"
            />
            <label for="userID">아이디</label>
          </div>
          <p v-if="userIDCheck && !userID">
            userID가 누락되었습니다.
          </p>
          <p v-if="userIDBlank">
            {{ logBlankuserID }}
          </p>
        </div>
        <div class="item">
          <div class="input-box">
            <input
              id="password"
              type="password"
              placeholder=" "
              v-model="password"
              autocomplete="off"
              @focus="afterFocus('password')"
              @keyup="isBlankVaild(password, 'password')"
            />
            <label for="password">비밀번호</label>
          </div>
          <p v-if="passwordCheck && !password">
            password가 누락되었습니다.
          </p>
          <p v-if="passwordBlank">
            {{ logBlankpassword }}
          </p>
        </div>
        <div style="width: 100%; height: 44px;">
          <div v-if="logMessage" class="logMessage">{{ logMessage }}</div>
        </div>
        <button :disabled="!userID || !password">로그인</button>
      </form>
    </div>
    <!-- modal -->
    <div class="modal" v-if="modalSwitch">
      <div class="dialog">
        <div class="screenAlert-icon screenAlert-success animate">
          <span
            class="screenAlert-line screenAlert-tip animateSuccessTip"
          ></span>
          <span
            class="screenAlert-line screenAlert-long animateSuccessLong"
          ></span>
          <div class="screenAlert-placeholder"></div>
          <div class="screenAlert-fix"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { BlankValid } from '@/utils/validation'
export default {
  data() {
    return {
      data: [1, 2, 3, 4, 5],
      userID: '',
      password: '',
      // log
      logMessage: '',
      logBlankuserID: '',
      logBlankpassword: '',
      // check
      userIDCheck: false,
      passwordCheck: false,
      // bool
      userIDBlank: false,
      passwordBlank: false,
      // modal switch
      modalSwitch: false,
    }
  },
  methods: {
    async submitForm() {
      const userData = {
        userID: this.userID,
        password: this.password,
      }
      this.logMessage = ''
      const res = await this.$store.dispatch('LOGIN', userData)
      if (res.message === 'fail') {
        this.logMessage = '정보를 정확히 입력해주세요'
      } else {
        this.modalSwitch = true
        setTimeout(() => this.$router.push('/main'), 1500)
      }
    },
    afterFocus(id) {
      const el = document.querySelector(`#${id}`)
      el.addEventListener('blur', () => {
        // 아무것도 안쓰고 나갔어?
        if (!this[`${id}`]) {
          this[`${id}Check`] = true
        }
      })
    },
    isBlankVaild(data, id) {
      if (BlankValid(this[`${id}`])) {
        this[`${id}Blank`] = true
        this[`logBlank${id}`] = '공백 사용할 수 없습니다.'
        this[`${id}`] = ''
      } else {
        this[`${id}Blank`] = false
      }
    },
  },
}
</script>

<style scoped src="./css/user/default.css"></style>

<style scoped src="./css/user/modal.css"></style>
<style scoped src="./css/user/Signup.css"></style>
<style scoped></style>
