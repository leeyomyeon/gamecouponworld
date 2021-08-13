<template>
  <div>
    <h1>Detail 페이지</h1>
    <!-- 댓글 뺀 상세 피드 컴포넌트 -->
    <FeedDetail :feedItem="feedItem"></FeedDetail>
    <!-- 댓글 상세 피드 -->
    <!-- <FeedComment :feedItem="feedItem"></FeedComment> -->
  </div>
</template>

<script>
import { fetchFeed } from '@/api/feed.js'
import FeedDetail from '@/components/feed/FeedDetail'
// import FeedComment from '@/components/feed/FeedComment'
export default {
  components: {
    FeedDetail,
    // FeedComment,
  },
  data() {
    return {
      feedItem: {},
    }
  },
  methods: {
    async fetchData(id) {
      try {
        // 피드 가져오기
        const response = await fetchFeed(id)
        this.feedItem = response.data
        // 댓글 가져오기
      } catch (error) {
        console.log(error)
      }
    },
  },
  created() {
    // 해당 피드에 대한 상세 정보 불러오기
    const id = this.$route.params.id
    this.fetchData(id)
  },
}
</script>

<style></style>
