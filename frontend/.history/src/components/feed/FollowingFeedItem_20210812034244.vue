<template>
  <div @click="moveDetail" class="feed-follow-body">
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
    <div class="feed-detail-bottom margin-sm">
      <!-- 좋아요 버튼 -->
      <div class="d-flex">
        <div>
          <button v-if="isLike" @click="incLike(feedItem.feedID)">
            <span><i class="far fa-heart heart"></i></span>
          </button>
          <button v-else @click="decLike(feedItem.feedID)">
            <span><i class="fas fa-heart heart"></i></span>
          </button>
        </div>
        <div class="i-am-like">
          <p v-if="isLike">{{ feedItem.likeCnt }} 명이 좋아합니다.</p>
          <p v-else-if="feedItem.likeCnt - 1 > 0">
            {{ userID }}님 외에 {{ feedItem.likeCnt - 1 }} 명이 좋아합니다.
          </p>
          <p v-else>{{ userID }}님이 좋아합니다.</p>
        </div>
      </div>

      <!-- 좋아요와 내용 정보가 나타나는 곳 -->
      <p class="margin-sm">좋아요 {{ feedItem.likeCnt }}개</p>
      <div class="feed-content">
        <p class="margin-sm feed-userID">{{ feedItem.userID }}</p>
        <p class="margin-sm follow-feed-content">{{ feedItem.content }}</p>
      </div>
      <em class="feed-date">
        {{ date }}
      </em>
    </div>
  </div>
</template>

<script>
import { fetchFeed, likeFeed, unlikeFeed } from '@/api/feed.js'
import { getUserFromCookie } from '@/utils/cookies.js'
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
      isLike: !this.feedItem.like,
      userID: getUserFromCookie(),
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
<style scoped src="../css/feed/feedDetail.css"></style>
