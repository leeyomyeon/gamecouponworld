<template>
  <div>
    <!-- 유저 정보가 나타나는 곳 -->
    <div class="feed-detail-top">
      <p>{{ feedItem.userID }}</p>
      <!-- 피드 삭제 버튼 -->
      <button v-if="isUserValid" @click="feedDelete">삭제</button>
    </div>
    <!-- 사진이 나타나는 곳 -->
    <div v-if="photos.length > 1" class="photo-zone d-flex">
      <button @click="moveSlideToLeft" style="width: 8%">&#60;</button>
      <img :src="photos[photoNum]" style="width: 85%" />
      <button @click="moveSlideToRight" style="width: 8%">&#62;</button>
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
        <p class="margin-sm">{{ feedItem.content }}</p>
      </div>
      <em class="feed-date">
        {{ date }}
      </em>
    </div>
    <div>
      <feed-comment-create-form />
    </div>
  </div>
</template>
<script>
// <i class="far fa-heart"></i
// >            <i class="fas fa-heart"></i
// >
import { fetchFeed, deleteFeed, likeFeed, unlikeFeed } from '@/api/feed.js'
import FeedCommentCreateForm from './FeedCommentCreateForm.vue'
import { getUserFromCookie } from '@/utils/cookies.js'
export default {
  components: { FeedCommentCreateForm },
  props: {
    feedItem: {
      type: Object,
      required: true,
    },
    isLike: {
      type: Boolean,
      required: true,
    },
    photos: {
      type: Array,
      required: true,
    },
    date: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      tempLike: null,
      userID: getUserFromCookie(),
      photoNum: 0,
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
      // emit
      this.tempLike = false
      this.$emit('changeLike', this.tempLike)
      // this.isLike = false
    },
    async decLike(id) {
      const data = new FormData()
      data.append('userID', this.$store.state.userID)
      data.append('feedID', this.feedItem.feedID)
      await unlikeFeed(data)
      // 피드 가져오기
      const response = await fetchFeed(id)
      this.feedItem.likeCnt = response.data.likeCnt
      // emit
      this.tempLike = true
      this.$emit('changeLike', this.tempLike)
      // this.isLike = true
    },
    moveSlideToRight() {
      if (this.photos.length - 1 == this.photoNum) {
        this.photoNum = 0
      } else {
        this.photoNum += 1
      }
      console.log(this.photoNum)
    },
    moveSlideToLeft() {
      if (this.photoNum == 0) {
        this.photoNum = this.photos.length - 1
      } else {
        this.photoNum -= 1
      }
      console.log(this.photoNum)
    },
  },
  computed: {
    isUserValid() {
      return this.$store.state.userID === this.feedItem.userID
    },
  },
}
</script>
<style scoped src="../css/feed/feedDetail.css"></style>
