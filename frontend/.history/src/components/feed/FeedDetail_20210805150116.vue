<template>
  <div>
    <div>userID: {{ feedItem.userID }}</div>
    <div>feedID: {{ feedItem.feedID }}</div>
    <div>내용: {{ feedItem.content }}</div>
    <div>날짜: {{ feedItem.date }}</div>
    <div>좋아요: {{ feedItem.likeCnt }}</div>
    <div v-for="photo in photos" :key="photo.id">
      {{ photo }}
    </div>
    <!-- 좋아요 버튼 -->
    <button v-if="isLike" @click="incLike(feedItem.feedID)">like</button>
    <button v-else @click="decLike(feedItem.feedID)">unlike</button>
    <!-- 피드 삭제 버튼 -->
    <button v-if="isUserValid" @click="feedDelete">삭제</button>
  </div>
</template>
<script>
// <i class="far fa-heart"></i
// >            <i class="fas fa-heart"></i
// >
import {
  fetchFeed,
  deleteFeed,
  isUserLike,
  likeFeed,
  unlikeFeed,
} from '@/api/feed.js'
export default {
  props: {
    feedItem: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      isLike: true,
      photos: [],
    }
  },
  methods: {
    async feedDelete() {
      await deleteFeed(this.$store.state.userID, this.feedItem.feedID)
      this.$router.push('/feed')
    },
    async incLike(id) {
      const data = new FormData()
      data.append('userID', this.$store.state.userID)
      data.append('feedID', this.feedItem.feedID)
      await likeFeed(data)
      // 피드 가져오기
      const response = await fetchFeed(id)
      this.feedItem.likeCnt = response.data.likeCnt
      this.isLike = false
    },
    async decLike(id) {
      const data = new FormData()
      data.append('userID', this.$store.state.userID)
      data.append('feedID', this.feedItem.feedID)
      await unlikeFeed(data)
      // 피드 가져오기
      const response = await fetchFeed(id)
      this.feedItem.likeCnt = response.data.likeCnt
      this.isLike = true
    },
  },
  computed: {
    isUserValid() {
      return this.$store.state.userID === this.feedItem.userID
    },
  },
  async created() {
    const userID = this.$store.state.userID
    const { data } = await isUserLike(userID)
    for (var i = 0; i < data.length; i++) {
      if (data[i] !== null) {
        if (this.feedItem.feedID == data[i]['feedID']) {
          this.isLike = false
          break
        }
      }
    }
    this.photos = this.feedItem.photoPath.split('|')
  },
}
</script>

<style></style>
