<template>
  <div @click="move()" style="margin-bottom :2vw">
    <img
      :src="photoPath"
      alt=""
      style="width: 29vw; height: 30vw; padding: 0 1vw;"
    />
  </div>
</template>

<script>
export default {
  props: {
    feed: Object,
  },
  data() {
    return {
      photoPath: '',
    }
  },
  created() {
    var photo = this.feed.photoPath.split('|')
    this.photoPath = photo[0]
  },
  methods: {
    move() {
      this.$router.push('/feed/' + this.feed.feedID)
    },
  },
}
</script>

<style></style>
