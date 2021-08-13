<template>
  <div>
    <div @click="moveDetail">
      <!-- <div>userID: {{ feedItem.userID }}</div>
    <div>feedID: {{ feedItem.feedID }}</div>
    <div>내용: {{ feedItem.content }}</div>
    <div>날짜: {{ feedItem.date }}</div>
    <div>좋아요: {{ feedItem.likeCnt }}</div> -->
      <div>
        <div>userID: {{ feedItem.userID }}</div>
        <div>대표사진: {{ photos[0] }}</div>
        <div>내용: {{ feedItem.content }}</div>
        <div>좋아요누를사람임? : {{ feedItem.like }}</div>
      </div>
      <!-- <div style=" margin-bottom: 2vw;">
      <img
        :src="photos[0]"
        style="width: 30vw; height: 30vw; padding: 0 1vw;"
      />
    </div> -->
    </div>
    <div>
      <button v-if="!isLike" @click="incLike">좋아요</button> <br />
      <button v-if="isLike" @click="decLike">좋아요취소</button>
      <br />
    </div>
  </div>
</template>

<script>
import { fetchFeed, likeFeed, unlikeFeed } from '@/api/feed.js'
export default {
  props: {
    feedItem: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      photos: [],
      isLike: this.feedItem.like,
    }
  },
  methods: {
    // 해당 게시글 클릭시 상세페이지 이동
    moveDetail() {
      this.$router.push(`/feed/${this.feedItem.feedID}`)
    },
    // 유저 클릭시 유저Profile 이동
    moveProfile() {},
    // 좋아요 클릭시
    async incLike() {
      const data = new FormData()
      data.append('userID', this.$store.state.userID)
      data.append('feedID', this.feedItem.feedID)
      await likeFeed(data)
      // 피드 가져오기
      const response = await fetchFeed(this.feedItem.feedID)

      this.feedItem.likeCnt = response.data.likeCnt
      // emit
      this.isLike = true
    },
    async decLike() {
      const data = new FormData()
      data.append('userID', this.$store.state.userID)
      data.append('feedID', this.feedItem.feedID)
      await unlikeFeed(data)
      const response = await fetchFeed(this.feedItem.feedID)
      this.feedItem.likeCnt = response.data.likeCnt
      // emit
      this.isLike = false
    },
  },
  created() {
    this.photos = this.feedItem.photoPath.split('|')
  },
}
</script>
<style scoped></style>
