<template>
  <div>
    <div class="container">
      <div class="manager">
        <button @click="openPage">
          <i class="fas fa-cog fa-lg"></i>
        </button>
      </div>
      <div v-if="isOpenPage" class="modal">
        <div class="dialog">
          <button
            @click="openPage"
            style="width: 100%; display: flex; justify-content: flex-end;"
          >
            <i class="fas fa-times-circle fa-lg" style="color:#ffa061;"></i>
          </button>
          <div class="btn-fm-box">
            <div class="afk-plus">
              <button @click="openUid" style="color: #fff;">+AFK아레나</button>
            </div>
            <div v-if="isOpenUid">
              <form @submit.prevent="submitForm" class="form-box">
                <input type="text" v-model="uid" placeholder="UID" />
                <div class="form-box-div">
                  <p>프로필->정보->ID</p>
                  <button type="submit">등록</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { updateUid } from '@/api/main'
import { getUserFromCookie } from '@/utils/cookies.js'
export default {
  props: {
    userData: {
      type: [Object, String],
      required: true,
    },
  },
  data() {
    return {
      isOpenPage: false,
      isOpenUid: false,
      uid: this.userData.afkarenaUID,
    }
  },
  methods: {
    openPage() {
      this.isOpenPage = !this.isOpenPage
    },
    openUid() {
      this.isOpenUid = !this.isOpenUid
    },
    async submitForm() {
      const data = {
        afkarenaUID: this.uid,
        userID: getUserFromCookie(),
      }
      console.log(data)
      let response = await updateUid(data)
      console.log(response)
      this.isOpenUid = !this.isOpenUid
      // 현재 부모 data를 자식에서 바꿔서 문제가 발생 emit을 보내야함
      this.$emit(
        'changeUserData',
        await this.$store.dispatch('getGameUid', getUserFromCookie()),
      )
      setTimeout(() => (this.isOpenPage = !this.isOpenPage), 500)
    },
  },
  created() {
    this.uid = this.userData.afkarenaUID
  },
}
</script>

<style scoped src="./css/user/default.css"></style>
<style scoped src="./css/main/manager.css"></style>
<style scoped></style>
