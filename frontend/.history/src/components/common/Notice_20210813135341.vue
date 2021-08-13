<template>
  <div>
    <div @click="openModal">
      <span style="color:white; padding-left: 0.6rem;">
        <i class="far fa-bell fa-lg"></i>
      </span>
    </div>
    <div class="modal" v-if="isModal">
      <div class="dialog">
        <div v-for="msg in msgs" :key="msg.id" @click="readMsg(msg)">
          <div>{{ msg.sourceID }}님이 팔로우합니다.</div>
        </div>
        <span @click="goFollowdetail" class="follow-modal">더 보기</span>
        <span @click="openModal" class="delete-modal">닫기</span>
      </div>
    </div>
  </div>
</template>

<script>
import { getUserFromCookie } from '@/utils/cookies.js'
import { fetchMsg } from '@/api/notice.js'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
export default {
  data() {
    return {
      msgs: [],
      isModal: false,
    }
  },
  methods: {
    goFollowdetail() {
      this.$router.push('/user/requestFollowList')
      this.isModal = false
    },
    connect() {
      const serverURL = 'http://localhost:8078/stompTest'
      var sock = new SockJS(serverURL)
      var client = Stomp.over(sock)
      this.$store.dispatch('getClient', client)
      console.log(client)
      this.isStomp = true
      this.socket = client
      // 연결해
      client.connect({}, () => {
        console.log('Connected stompTest!!')
        const msg = {
          targetID: getUserFromCookie(),
        }
        // 이런식으로 구독할거야
        client.subscribe(`/topic/alarm/${getUserFromCookie()}`, event => {
          this.msgs = JSON.parse(event.body).reverse()
        })
        client.send('/AlarmCnt', JSON.stringify(msg), {})
      })
    },
    async readMsg(msgData) {
      console.log(msgData.targetID, msgData.sourceID)
      const data = new FormData()
      data.append('targetID', msgData.targetID)
      data.append('sourceID', msgData.sourceID)
      console.log('fetchMsg', data)
      await fetchMsg(data)
      const msg = {
        targetID: getUserFromCookie(),
      }
      let sending = this.$store.getters.client
      sending.send('/AlarmCnt', JSON.stringify(msg), {})
      this.$router.push('/user/requestFollowList')
    },
    openModal() {
      this.isModal = !this.isModal
    },
  },
  created() {
    this.connect()
  },
  // computed: {
  //   checkServerMsg() {
  //     return this.$store.getters.getServerMsg
  //   },
  // },
  // watch: {
  //   checkServerMsg(val) {
  //     this.msg = val
  //     console.log('notice.vue', this.msg)
  //   },
  // },
}
</script>

<style scoped></style>
<style scoped src="../css/user/default.css"></style>
<style scoped src="../css/user/modal.css"></style>
