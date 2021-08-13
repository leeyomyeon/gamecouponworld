<template>
  <div>
    <!-- 유저 정보가 나타나는 곳 -->
    <div class="feed-detail-top">
      <p>{{ feedItem.userID }}</p>
      <!-- 피드 삭제 버튼 -->
      <button v-if="isUserValid" @click="feedDelete">삭제</button>
    </div>
    <!-- 사진이 나타나는 곳 -->
    <div class="photo-zone">
      <div v-for="photo in photos" :key="photo.id">
        <img :src="photo" style="width: 100%" />
      </div>
    </div>
    <!-- 하단부 -->
    <div class="feed-detail-bottom">
      <!-- 좋아요 버튼 -->
      <div class="heart margin-sm">
        <button v-if="isLike" @click="incLike(feedItem.feedID)">
          <i class="far fa-heart"></i>
        </button>
        <button v-else @click="decLike(feedItem.feedID)">
          <i class="fas fa-heart"></i>
        </button>
      </div>

      <!-- 좋아요와 내용 정보가 나타나는 곳 -->
      <p class="margin-sm">좋아요 {{ feedItem.likeCnt }}개</p>
      <p class="margin-sm">{{ feedItem.userID }}</p>
      <p class="margin-sm">{{ feedItem.content }}</p>
      <div>날짜: {{ feedItem.date }}</div>
    </div>
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
<style scoped src="../css/feed/feedDetail.css"></style>
<style></style>
