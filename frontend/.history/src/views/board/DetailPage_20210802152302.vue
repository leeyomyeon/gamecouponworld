<template>
  <div>
    <h1>게시글 상세페이지</h1>
    <div><BoardDetail></BoardDetail></div>
    <div><CommentCreateForm /></div>
  </div>
</template>

<script>
import BoardDetail from '@/components/board/BoardDetail.vue'

export default {
  components: {
    BoardDetail,
  },
}
</script>

<style></style>
