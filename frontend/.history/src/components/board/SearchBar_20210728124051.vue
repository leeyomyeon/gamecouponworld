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
      function searchBoard(searchOption, value, limit, offset) {
        return instance.get(`search/${searchOption}/${value}/${limit}/${offset}`)
      }
      const boardList = await searchBoard(this.boardTotal, 0)
      boardList = boardList.data
      console.log(boardList)
      const boardList = keyword
      this.$emit('on-keyword-enter', boardList)
      // },
    },
  },
}
</script>
