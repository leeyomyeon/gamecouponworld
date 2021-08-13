<template>
  <div>
    <li
      class="d-flex"
      v-for="(v, index) in requestFollowList"
      v-bind:key="index"
    >
      <span class="col-7">{{ v }}님이 팔로우 요청을 하였습니다.</span>
      <button class="btn-primary col-3" @click="allow(v)">팔로우</button>
    </li>
  </div>
</template>

<script>
import { getUserFromCookie } from '@/utils/cookies.js'
import { findrequestFollow, allowFollow } from '@/api/auth'
export default {
  data() {
    return {
      requestFollowList: [],
    }
  },
  async created() {
    await this.test()
  },
  methods: {
    async test() {
      const userData = {
        targetID: getUserFromCookie(),
      }
      const res = await findrequestFollow(userData)
      console.log(res)
      this.requestFollowList = res.data
    },
    async allow(v) {
      const temp = {
        sourceID: v,
        targetID: getUserFromCookie(),
      }
      const res = await allowFollow(temp)
      console.log(res)
      await this.test()
      // if (res.data == 'success') alert('팔로우 요청을 수락하였습니다.!')
    },
  },
}
</script>

<style scoped src="../css/user/userFollowList.css"></style>
