<template>
  <div>
    <h3>댓글 조회</h3>
    <ul>
      <li></li>
    </ul>
  </div>
</template>

<script>
import { detailComment } from '@/api/boards.js'
export default {
  data() {
    return {
      boardID: this.$route.params.id,
      commentList: {},
    }
  },
  async created() {
    this.commentList = await detailComment(this.boardID)
    console.log(this.commnetList)
  },
}
</script>
