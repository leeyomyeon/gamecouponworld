<template>
  <div>
    <h1>게시물 상세</h1>
  </div>
</template>

<script>
import data from '@/data'
export default {
  name: 'Read',
  data() {
    const board_order = this.$router.params.board_order
    return {
      data: data[board_order],
    }
  },
}
</script>
