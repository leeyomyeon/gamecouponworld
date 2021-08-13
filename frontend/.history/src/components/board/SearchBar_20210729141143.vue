<template>
  <div class="search-bar">
    <input type="text" @keyup.enter="onKeywordEnter" />
  </div>
</template>

<script>
import { searchBoard } from '@/api/boards'

export default {
  name: 'SearchBar',
  methods: {
    async onKeywordEnter(event) {
      const keyword = event.target.value
      const res = await searchBoard('title', keyword, 10, 0)
      const boardListTotal = res.data
      const boardTotal = boardListTotal.length
      console.log(boardListTotal)
      console.log(boardTotal)
      this.$emit('on-keyword-enter', { boardListTotal, boardTotal })
    },
  },
}
</script>
