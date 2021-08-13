<template>
  <div>
    <MainAppbar />
    <!-- 게임관리 버튼(컴포넌트) -->
    <div class="box">
      <GameManager
        :userData="userData"
        @changeUserData="changeUserData"
        class="item"
        style="margin-bottom:3%;"
      ></GameManager>
      <!-- 게임이미지 -->
      <img
        src="../assets/afkarena.png"
        alt=""
        style="width:100%;margin-bottom:2%;"
      />
      <!-- 모든쿠폰등록(흠...) -->
      <div class="regist-button">
        <button @click="openPage">자동등록</button>
      </div>
      <div v-if="isOpenPage" class="modal">
        <div class="dialog">
          <button
            style="width: 100%; display: flex; justify-content: flex-end;"
            @click="openPage"
          >
            <i class="fas fa-times-circle fa-lg" style="color:#ffa061;"></i>
          </button>
          <div class="btn-fm-box">
            <div class="afk-plus">
              <button style="color: #fff;">AFK아레나</button>
            </div>
            <form @submit.prevent="submitForm" class="form-box">
              <input
                id="authentication"
                type="text"
                v-model="authenValue"
                placeholder="인증번호"
              />
              <div class="form-box-div">
                <p>프로필->시스템->인증번호</p>
                <button type="submit">등록</button>
              </div>
            </form>
            <p
              v-if="logMsg"
              style="color: blue; font-weight: bold; font-size: 0.9rem;"
            >
              {{ logMsg }}
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MainAppbar from '@/components/MainAppbar.vue'
import { getUserFromCookie } from '@/utils/cookies.js'
import { registCoupon } from '@/api/main'
import GameManager from '@/components/GameManager.vue'
export default {
  components: {
    GameManager,
    MainAppbar,
  },
  data() {
    return {
      userData: {},
      isOpenPage: false,
      authenValue: '',
      logMsg: '',
    }
  },
  methods: {
    changeUserData(tempUserData) {
      this.userData = tempUserData
    },
    openPage() {
      this.isOpenPage = !this.isOpenPage
    },
    async submitForm() {
      const data = {
        userID: getUserFromCookie(),
        verifyCode: this.authenValue,
      }
      console.log(data)
      try {
        await registCoupon(data)
        this.logMsg = '[등록완료]우편을 확인해보세요.'
      } catch (error) {
        this.logMsg = '인증번호가 틀렸습니다.'
      }
    },
  },
  async created() {
    this.userData = await this.$store.dispatch(
      'getGameUid',
      getUserFromCookie(),
    )
    console.log(this.userData)
  },
}
</script>

<style scoped src="../components/css/user/default.css"></style>
<style scoped src="../components/css/main/mainbox.css"></style>
<style scoped></style>
