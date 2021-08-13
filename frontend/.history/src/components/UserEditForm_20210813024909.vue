<template>
  <div class="box">
    <form @submit.prevent="submitForm" class="form-box">
      <div class="item img-box" @click="imgClick">
        <!-- 기본이미지 구현-->
        <div v-if="profilePath == '' && preview == null">
          <img
            src="../assets/profileDefault.jpg"
            style="width: 30vw; height: 30vw;"
          />
        </div>
        <div v-else-if="preview == null">
          <img
            :src="picture"
            style="  border: 1px solid #333;border-radius: 50%;width: 30vw; height: 30vw;"
          />
        </div>
        <div v-else>
          <img
            :src="preview"
            style="border: 1px solid #333;width: 30vw; height: 30vw;border-radius: 50%;"
          />
        </div>
        <div>
          <input
            type="file"
            @change="previewImage"
            accept="image/*"
            id="addImg"
          />
        </div>
      </div>
      <!-- <div class="item">
        <label for="userID">userID: </label>
        <input
          id="userID"
          type="text"
          autocomplete="off"
          v-model="userID"
          disabled
        />
        <p v-if="!isUserIdLenValid">
          userID 길이 초과하였습니다.
        </p>
        <p v-if="!isUserIdValid">
          영어, 숫자 조합만 가능합니다.
        </p>
      </div> -->
      <div class="item">
        <div class="input-box">
          <input
            id="alias"
            type="text"
            placeholder=" "
            spellcheck="false"
            autocomplete="off"
            v-model="alias"
          />
          <label for="alias">닉네임</label>
        </div>

        <p v-if="!isAliasValid">
          특수문자,공백 사용할 수 없습니다.
        </p>
      </div>
      <div class="item">
        <div class="input-box">
          <textarea
            id="bio"
            type="text"
            placeholder=" "
            spellcheck="false"
            autocomplete="off"
            v-model="bio"
          />
          <label for="bio">인사말</label>
        </div>
        <p v-if="!bioBlank">
          공백 사용할 수 없습니다.
        </p>
      </div>
      <p style="font-size: 0.8rem;font-weight: bold; margin-right:1em;">
        비밀번호 변경
      </p>
      <input
        type="checkbox"
        autocomplete="off"
        id="passwordCheckbox"
        @click="passwordActive"
      />
      <div v-if="isPwAcitve" class="pw-box">
        <div class="item">
          <div class="input-box-pw">
            <input
              id="passwordCurrent"
              type="password"
              placeholder=" "
              spellcheck="false"
              autocomplete="off"
              v-model="passwordCurrent"
              :disabled="!isPwAcitve"
              @keyup="isBlankVaild(passwordCurrent, 'passwordCurrent')"
            />
            <label for="passwordCurrent">현재 비밀번호</label>
          </div>
          <p v-if="!isPasswordLenValid">
            password 길이 초과하였습니다.
          </p>
          <p v-if="logMessage">
            {{ logMessage }}
          </p>
          <p v-if="isPwAcitve && passwordCurrentBlank">
            {{ logBlankpasswordCurrent }}
          </p>
        </div>
        <div class="item">
          <div class="input-box-pw">
            <input
              id="passwordChange"
              type="password"
              placeholder=" "
              spellcheck="false"
              autocomplete="off"
              v-model="passwordChange"
              :disabled="!isPwAcitve"
              @keyup="isBlankVaild(passwordChange, 'passwordChange')"
            />
            <label for="passwordChange">변경할 비밀번호</label>
          </div>
          <p v-if="isPwAcitve && passwordChangeBlank">
            {{ logBlankpasswordChange }}
          </p>
        </div>
        <div class="item">
          <div class="input-box-pw">
            <input
              id="passwordChangeConfirm"
              type="password"
              placeholder=" "
              spellcheck="false"
              autocomplete="off"
              v-model="passwordChangeConfirm"
              :disabled="!isPwAcitve"
            />
            <label for="passwordChangeConfirm">변경할 비밀번호 확인</label>
          </div>
          <p v-if="passwordChangeConfirm && !isPasswordConfirmValid">
            비밀번호가 옳바르지 않습니다.
          </p>
        </div>
      </div>
      <button
        style="width: 100%; height:2em; padding:0;"
        type="submit"
        :disabled="
          !alias ||
            !bio ||
            isChange ||
            !isEmailLenValid ||
            !isAliasValid ||
            (isPwAcitve &&
              (!passwordCurrent ||
                !passwordChange ||
                !passwordChangeConfirm ||
                !isPasswordConfirmValid))
        "
      >
        수정
      </button>
    </form>
    <div class="deleteButton">
      <button
        @click="deleteAccount"
        style="margin: 0; height:1.4em; padding: 0 0.5em; background: white; color: #dadada font-weight:lighter!important;"
      >
        회원탈퇴
      </button>
    </div>
  </div>
</template>

<script>
import { getUserFromCookie, deleteCookie } from '@/utils/cookies'
import {
  validateEmail,
  UserIdValid,
  UsernameValid,
  BlankValid,
} from '@/utils/validation'
import { fetchUser, editUser, deleteUser } from '@/api/auth'

export default {
  data() {
    return {
      userID: '',
      userName: '',
      password: '',
      passwordCurrent: '',
      passwordChange: '',
      passwordChangeConfirm: '',
      email: '',
      alias: '',
      profilePath: '',
      bio: '',
      tempAlias: '',
      tempBio: '',
      // checkbox
      isPwAcitve: false,
      // log
      logMessage: '',
      logBlankpasswordCurrent: '',
      logBlankpasswordChange: '',
      // bool
      passwordCurrentBlank: false,
      passwordChangeBlank: false,
      bioBlank: true,
      //이미지데이터
      imageData: null,
      picture: null,
      preview: null,
    }
  },
  computed: {
    isChange() {
      return (
        !this.isPwAcitve &&
        this.bio === this.tempBio &&
        this.alias === this.tempAlias
      )
    },
    // 길이
    isUserIdLenValid() {
      return this.userID.length <= 18
    },
    isUsernameLenValid() {
      return this.userName.length <= 20
    },
    isPasswordLenValid() {
      return this.passwordChange.length <= 18
    },
    isEmailLenValid() {
      return this.email.length <= 30
    },
    // isAliasLenValid() {
    //   return this.alias.length <= 16
    // },
    isAliasValid() {
      return UsernameValid(this.alias)
    },
    // 영어, 숫자 허용만 가능
    isUserIdValid() {
      return UserIdValid(this.userID)
    },
    // 한글, 영어, 숫자 허용
    isUsernameValid() {
      return UsernameValid(this.userName)
    },
    isPasswordConfirmValid() {
      return this.passwordChange === this.passwordChangeConfirm
    },
    isEmailValid() {
      return validateEmail(this.email)
    },
    // isBioLenValid() {
    //   return this.bio.length <= 30
    // },
  },
  async created() {
    const userData = {
      userID: getUserFromCookie(),
    }
    const { data } = await fetchUser(userData)
    this.userID = data.userInfo.userID
    this.userName = data.userInfo.userName
    this.password = data.userInfo.password
    this.email = data.userInfo.email
    this.alias = data.userInfo.alias
    this.tempAlias = data.userInfo.alias
    this.tempBio = data.userInfo.bio
    this.profilePath = data.userInfo.profilePath
    this.bio = data.userInfo.bio
    this.picture = data.userInfo.profilePath
  },
  methods: {
    imgClick() {
      document.getElementById('addImg').click()
    },
    previewImage(event) {
      this.picture = null
      this.imageData = event.target.files[0]
      this.preview = URL.createObjectURL(event.target.files[0])
    },

    passwordActive() {
      this.isPwAcitve = !this.isPwAcitve
      this.passwordCurrent = ''
      this.passwordChange = ''
      this.passwordChangeConfirm = ''
      this.passwordCurrentBlank = false
    },
    async submitForm() {
      try {
        if (this.bio.trim() === '') {
          this.logBlankbio = '공백 사용할 수 없습니다.'
          this.bioBlank = false
          return
        }
        if (this.isPwAcitve) {
          if (this.password !== this.passwordCurrent) {
            this.logMessage = '암호가 정확하지 않습니다.'
            return
          } else {
            this.password = this.passwordChange
          }
        }
        const data = new FormData()
        data.append('userID', this.userID)
        data.append('userName', this.userName)
        data.append('password', this.password)
        data.append('email', this.email)
        data.append('alias', this.alias)
        data.append('imageData', this.imageData)
        data.append('bio', this.bio)
        data.append('profilePath', this.profilePath)
        await editUser(data)
        this.$router.push(`/user/profile/${getUserFromCookie()}`)
      } catch (error) {
        console.log(error)
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
    async deleteAccount() {
      const userData = {
        userID: this.userID,
      }
      await deleteUser(userData)
      deleteCookie('one_user')
      deleteCookie('one_auth')
      this.$store.commit('clearUserid')
      this.$store.commit('clearToken')
      this.$router.push('/account/login')
    },
  },
}
</script>

<style scoped src="./css/user/default.css"></style>
<style scoped src="./css/user/edit.css"></style>
<style scoped></style>
