<template>
  <div>
    <h1>수정페이지</h1>
    <FeedUpdateForm :feedItem="feedItem"></FeedUpdateForm>
  </div>
</template>

<script>
import FeedUpdateForm from '@/components/feed/FeedUpdateForm.vue'
import { fetchFeed } from '@/api/feed.js'
export default {
  components: {
    FeedUpdateForm,
  },
  data() {
    return {
      feedItem: [],
    }
  },
  methods: {
    async fetchData(id) {
      try {
        // 피드 가져오기
        const response = await fetchFeed(id)
        this.feedItem = response
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
