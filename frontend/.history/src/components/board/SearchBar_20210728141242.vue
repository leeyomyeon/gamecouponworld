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
      const boardList = res.data
      this.$emit('on-keyword-enter', boardList)
    },
  },
}
</script>
