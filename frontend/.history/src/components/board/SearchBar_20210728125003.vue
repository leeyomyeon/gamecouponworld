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
      console.log(keyword)
      const boardList = await searchBoard(title, keyword, 1, 0)
      boardList = boardList.data
      this.$emit('on-keyword-enter', boardList)
    },
  },
}
</script>
