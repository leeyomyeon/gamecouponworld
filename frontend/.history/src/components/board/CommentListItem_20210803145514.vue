<template>
  <div>
    <h3>댓글 조회</h3>
    <ul>
      <li v-for="comment in commentList">
        {{ comment.content }}
      </li>
    </ul>
  </div>
</template>

<script>
import { detailComment } from '@/api/boards.js'
export default {
  data() {
    return {
      boardID: this.$route.params.id,
      commentList: '',
    }
  },
  async created() {
    const commentList = await detailComment(this.boardID)
    this.commentList = commentList.data
    console.log(this.commnetList)
  },
}
</script>
