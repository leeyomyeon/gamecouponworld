<template>
  <div>
    <h1>게시글 상세페이지</h1>
    <div><BoardDetail></BoardDetail></div>
  </div>
</template>

<script>
import BoardDetail from '@/components/board/BoardDetail.vue'
import CommentCreateForm from '@/components/board/CommentCreateForm.vue'

export default {
  components: {
    BoardDetail,
    CommentCreateForm,
  },
}
</script>

<style></style>
