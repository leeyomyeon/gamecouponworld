<template>
  <div>
    <FeedAppbar />
    <!-- 댓글 뺀 상세 피드 컴포넌트 -->
    <FeedDetail
      :feedItem="feedItem"
      :isLike="isLike"
      :photos="photos"
      :date="date"
      @changeLike="changeLike"
    ></FeedDetail>
    <!-- 댓글 상세 피드 -->
    <!-- <FeedComment :feedItem="feedItem"></FeedComment> -->
  </div>
</template>

<script>
import { fetchFeed, isUserLike } from '@/api/feed.js'
import FeedDetail from '@/components/feed/FeedDetail'
import FeedAppbar from '@/components/feed/FeedAppbar.vue'
// import FeedComment from '@/components/feed/FeedComment'
export default {
  components: {
    FeedDetail,
    FeedAppbar,
    // FeedComment,
  },
  data() {
    return {
      feedItem: {},
      isLike: true,
      photos: [],
      date: '',
    }
  },
  methods: {
    changeLike(tempLike) {
      this.isLike = tempLike
    },
  },
  async created() {
    // 해당 피드에 대한 상세 정보 불러오기
    const id = this.$route.params.id

    const response = await fetchFeed(id)
    this.feedItem = response.data

    const userID = this.$store.state.userID
    const { data } = await isUserLike(userID)
    for (var i = 0; i < data.length; i++) {
      if (this.feedItem.feedID == data[i]['feedID']) {
        this.isLike = false
        break
      }
    }
    this.photos = this.feedItem.photoPath.split('|')
    this.date = this.feedItem.date
      .substring(5, 7)
      .concat('월', this.feedItem.date.substring(8, 10), '일')
  },
}
</script>

<style></style>
