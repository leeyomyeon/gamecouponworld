<template>
  <li @click="moveDetail">
    <div>{{ boardItem.subject }}</div>
    <div>{{ boardItem.content }}</div>
    <div>{{ boardItem.writer }}</div>
    <div>{{ boardItem.date }}</div>
    <div>{{ boardItem.recommend }}</div>
    <div>{{ boardItem.viewCount }}</div>
    <br />
  </li>
</template>

<script>
export default {
  props: {
    boardItem: {
      type: Object,
      default: () => ({ message: '찾을 수 없습니다.' }),
      // 부모 -> 자식 컴포넌트 사용할 때 값을 항상 넣어줘야함
      // required: true,
    },
  },
  methods: {
    moveDetail() {
      this.$router.push(`/board/${this.boardItem.boardID}`)
    },
  },
}
</script>

<style></style>
