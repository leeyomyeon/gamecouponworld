<template>
  <div>
    <h1>자유 게시판dd</h1>
    <button @click="write" type="button" class="btn btn-light">글쓰기</button>
  </div>
</template>

<script>
export default {
  methods: {
    write() {
      this.$router.push({
        path: '/board/create',
      })
    },
  },
}
</script>
