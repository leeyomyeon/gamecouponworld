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
      const res = await searchBoard('title', keyword, 3, 1)
        .then(() => {
          console.log(res)
          const boardList = res.data
          console.log(boardList)
          this.$emit('on-keyword-enter', boardList)
        })
        .catch(() => {
          console.error('검색 실패')
        })
    },
  },
}
</script>
