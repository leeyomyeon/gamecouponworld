<template>
  <div>
    <h1>게시물 상세</h1>
    <div>
      <h3>{{ data.title }}</h3>
      <p>{{ data.writer }}</p>
      <p>{{ data.date }}</p>
      <p>{{ data.content }}</p>
      <p>{{ data.views }}</p>
      <p>{{ data.recommends }}</p>
    </div>
  </div>
  <p>{{ $route.params.border_idx }}</p>
</template>

<script>
import data from '@/data'
export default {
  name: 'Detail',
  data() {
    const board_order = this.$router.params.board_order
    return {
      data: data[board_order],
    }
  },
}
</script>
