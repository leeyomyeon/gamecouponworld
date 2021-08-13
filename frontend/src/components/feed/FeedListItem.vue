<template>
  <div @click="moveDetail">
    <!-- <div>userID: {{ feedItem.userID }}</div>
    <div>feedID: {{ feedItem.feedID }}</div>
    <div>내용: {{ feedItem.content }}</div>
    <div>날짜: {{ feedItem.date }}</div>
    <div>좋아요: {{ feedItem.likeCnt }}</div> -->
    <div style=" margin-bottom: 2vw;">
      <img
        :src="photos[0]"
        style="width: 29vw; height: 30vw; padding: 0 1vw;"
      />
    </div>
  </div>
</template>

<script>
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
    clickLike() {},
  },
  created() {
    this.photos = this.feedItem.photoPath.split('|')
  },
}
</script>
<style scoped></style>
