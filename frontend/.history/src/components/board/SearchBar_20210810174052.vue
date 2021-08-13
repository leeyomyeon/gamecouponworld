<template>
  <div class="search-bar">
    <div class="d-flex bg-primary searchBar">
      <input type="text" v-model="userInput" @keyup.enter="onKeywordEnter" />
      <button
        class="btn btn-primary d-flex justify-content-center align-items-center"
        @click="onKeywordEnter"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="16"
          height="16"
          fill="currentColor"
          class="bi bi-search"
          viewBox="0 0 16 16"
        >
          <path
            d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"
          />
        </svg>
      </button>
    </div>
  </div>
</template>

<script>
import { searchBoard, CountBoard } from '@/api/boards'

export default {
  data() {
    userInput: '',
  }
  name: 'SearchBar',
  methods: {
    async onKeywordEnter() {
      const keyword = this.userInput
      const TotalBoard = await CountBoard()
      const res = await searchBoard('title', keyword, TotalBoard.data, 0)
      const boardListTotal = res.data
      const boardTotal = boardListTotal.length
      this.$emit('on-keyword-enter', boardListTotal, boardTotal)
    },
  },
}
</script>

<style>
.btn-primary {
  background-color: #ffa061;
  border: none;
  border-radius: 0;
  box-shadow: none;
}
.btn-primary:visited {
  background-color: #fa904d;
  border: none;
}
.btn-primary:active {
  background-color: #fa904d;
  border: none;
}
.btn-primary:hover {
  background-color: #fa904d;
  border: none;
}
.bg-primary {
  background-color: #ffa061;
  border: 4rem;
  border-radius: 10px;
}
input {
  border: solid 2px #ffa061;
  box-shadow: none;
  border-radius: 8px;
}
input:focus {
  outline: none;
  box-shadow: none;
}
.searchBar {
  padding: 3px;
  background-clip: padding-box;
  background-color: #ffa061 !important;
  border-radius: 10px;
}
</style>
