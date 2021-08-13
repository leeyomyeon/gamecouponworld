!<template>
  <div>
    <b-container class="bv-example-row">
      <b-row>
        <b-col>
          <li
            v-for="(v, index) in followerList"
            v-bind:key="index"
            @click="move(v)"
          >
            {{ v }}
          </li>
        </b-col>

        <b-col>
          <li v-for="v in followingList" v-bind:key="v" @click="move(v)">
            {{ v }}
          </li>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
// import { getUserFromCookie } from '@/utils/cookies.js'
import { findFollower, findFollowing } from '@/api/auth'
export default {
  data() {
    return {
      followerList: [],
      followingList: [],
    }
  },
  async created() {
    const userData = {
      userID: this.$route.query.userID,
    }
    const res = await findFollower(userData)
    const res2 = await findFollowing(userData)
    this.followerList = res.data
    this.followingList = res2.data
  },
  methods: {
    move(v) {
      this.$router.push(`/user/profile/${v}`)
      //ㄴ
    },
  },
}
</script>

<style></style>
