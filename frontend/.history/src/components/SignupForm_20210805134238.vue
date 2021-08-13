<template>
  <div class="box">
    <div class="container">
      <div>
        <form @submit.prevent="submitForm">
          <p class="sub-title">회원 가입</p>
          <div class="item">
            <div class="input-box">
              <input
                id="userID"
                type="text"
                autocomplete="off"
                v-model="userID"
                placeholder=" "
              />
              <label for="userID">아이디</label>
            </div>
            <p v-if="!isUserIdLenValid">
              userID 길이 초과하였습니다.
            </p>
            <p v-if="!isUserIdValid">
              영어, 숫자 조합만 가능합니다.
            </p>
          </div>

          <div class="item">
            <div class="input-box">
              <input
                id="userName"
                type="text"
                autocomplete="off"
                v-model="userName"
                placeholder=" "
              />
              <label for="userName">사용자 이름</label>
            </div>
            <p v-if="!isUsernameLenValid">
              userName 길이 초과하였습니다.
            </p>
            <p v-if="!isUsernameValid">
              특수문자,공백 사용할 수 없습니다.
            </p>
          </div>

          <div class="item">
            <div class="input-box">
              <input
                id="password"
                type="text"
                autocomplete="off"
                v-model="password"
                placeholder=" "
              />
              <label for="password">비밀번호</label>
            </div>
            <p v-if="!isPasswordLenValid">
              password 길이 초과하였습니다.
            </p>
            <p v-if="passwordBlank">
              {{ logBlankpassword }}
            </p>
          </div>
          <div class="item">
            <div class="input-box">
              <input
                id="passwordConfirm"
                type="text"
                autocomplete="off"
                v-model="passwordConfirm"
                placeholder=" "
              />
              <label for="passwordConfirm">비밀번호 확인</label>
            </div>
          </div>
          <p v-if="passwordConfirm && !isPasswordConfirmValid">
            비밀번호가 옳바르지 않습니다.
          </p>
          <div class="item">
            <div class="input-box">
              <input
                id="email"
                type="text"
                autocomplete="off"
                v-model="email"
                placeholder=" "
                disabled
              />
              <label for="email">이메일 </label>
            </div>
            <!-- <p v-if="!isEmailValid && email">
          이메일 형식이 아닙니다.
        </p>
        <p v-if="!isEmailLenValid">
          email 길이 초과하였습니다.
        </p> -->
          </div>
          <div class="item">
            <div class="input-box">
              <input
                id="alias"
                type="text"
                autocomplete="off"
                v-model="alias"
                placeholder=" "
              />
              <label for="alias">닉네임 </label>
            </div>
            <p v-if="!isAliasLenValid">
              alias 길이 초과하였습니다.
            </p>
            <p v-if="!isAliasValid">
              특수문자,공백 사용할 수 없습니다.
            </p>
          </div>
          <div class="item">
            <div class="input-box">
              <input
                id="profilePath"
                type="text"
                autocomplete="off"
                v-model="profilePath"
                placeholder=" "
              />
              <label for="profilePath">파일경로</label>
            </div>
          </div>
          <div class="item">
            <div class="input-box">
              <textarea
                id="bio"
                type="text"
                placeholder=""
                rows="3"
                v-model="bio"
              />
              <label for="bio">자기소개 </label>
            </div>
            <p v-if="!isBioLenValid">
              30자 이내로 작성해주세요.
            </p>
            <p v-if="!bioBlank">
              공백 사용할 수 없습니다.
            </p>
          </div>
          <p>{{ logMessage }}</p>
          <button
            type="submit"
            :disabled="
              !userID ||
                !userName ||
                !password ||
                !passwordConfirm ||
                !isPasswordConfirmValid ||
                !email ||
                !isEmailValid ||
                !alias ||
                !bio ||
                !isBioLenValid ||
                !isUserIdLenValid ||
                !isUsernameLenValid ||
                !isPasswordLenValid ||
                !isEmailLenValid ||
                !isAliasLenValid ||
                !isUserIdValid ||
                !isUsernameValid ||
                !isAliasValid
            "
          >
            회원가입
          </button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { registerUser } from '@/api/auth'
import {
  validateEmail,
  UserIdValid,
  UsernameValid,
  BlankValid,
} from '@/utils/validation'

export default {
  data() {
    return {
      userID: '',
      userName: '',
      password: '',
      passwordConfirm: '',
      email: '',
      alias: '',
      profilePath: '',
      bio: '',
      // log
      logMessage: '',
      logBlankpassword: '',
      //bool
      passwordBlank: false,
      bioBlank: true,
    }
  },
  computed: {
    // 길이
    isUserIdLenValid() {
      return this.userID.length <= 18
    },
    isUsernameLenValid() {
      return this.userName.length <= 20
    },
    isPasswordLenValid() {
      return this.password.length <= 18
    },
    isEmailLenValid() {
      return this.email.length <= 30
    },
    isAliasLenValid() {
      return this.alias.length <= 16
    },
    // 영어, 숫자 허용만 가능
    isUserIdValid() {
      return UserIdValid(this.userID)
    },
    // 한글, 영어, 숫자 허용
    isUsernameValid() {
      return UsernameValid(this.userName)
    },
    isAliasValid() {
      return UsernameValid(this.alias)
    },
    isPasswordConfirmValid() {
      return this.password === this.passwordConfirm
    },
    isEmailValid() {
      return validateEmail(this.email)
    },
    isBioLenValid() {
      return this.bio.length <= 30
    },
  },
  methods: {
    async submitForm() {
      try {
        if (this.bio.trim() === '') {
          this.bioBlank = false
          return
        }
        const userData = {
          userID: this.userID,
          userName: this.userName,
          password: this.password,
          email: this.email,
          alias: this.alias,
          profilePath: this.profilePath,
          bio: this.bio,
        }
        await registerUser(userData)
        this.$router.push('/account/login')
      } catch (error) {
        this.logMessage = '이미 존재하는 userID 입니다.'
      }
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
  created() {
    this.email = this.$route.query.email
  },
}
</script>

<style scoped src="./css/user/default.css"></style>
<style scoped src="./css/user/Signup.css"></style>
<style scoped></style>
