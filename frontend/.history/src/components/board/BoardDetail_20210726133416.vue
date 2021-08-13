<template>
  <div>
    <h2>해당 게시글</h2>
  </div>
</template>

<script>
import { fetchBoard } from '@/api/boards'

export default {
  async created() {
    const boardID = this.$route.params.id
    console('보드디테일 요청보낼 데이터', boardID)
    const response = await fetchBoard(boardID)
    console('보드디테일 응답받은 데이터', response)
  },
}
</script>

<style></style>
