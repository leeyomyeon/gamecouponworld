<template>
  <div></div>
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
