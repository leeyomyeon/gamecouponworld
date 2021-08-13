<template>
  <div>
    <h1>게시글 상세페이지</h1>
    <div><BoardDetail></BoardDetail></div>
    <div v-if="userLogin"><CommentCreateForm /></div>
  </div>
</template>

<script>
import BoardDetail from '@/components/board/BoardDetail.vue'
import CommentCreateForm from '@/components/board/CommentCreateForm.vue'
import { getUserFromCookie } from '@/utils/cookies.js'
import BoardAppbar from '@/components/board/BoardAppbar.vue'

export default {
  computed: {
    userLogin() {
      return getUserFromCookie()
    },
  },
  components: {
    BoardDetail,
    CommentCreateForm,
    BoardAppbar,
  },
}
</script>

<style></style>
