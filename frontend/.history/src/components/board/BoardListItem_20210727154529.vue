<template>
  <!-- 밑에 @click="detail(idx) -->
  <tr :key="idx" v-for="(value, idx) in listItem">
    <th scope="row">{{ value.order }}</th>
    <td>{{ value.category }}</td>
    <td>{{ value.title }}</td>
    <td>{{ value.writer }}</td>
    <td>{{ value.date }}</td>
    <td>{{ value.viewCount }}</td>
    <td>{{ value.recommend }}</td>
  </tr>
</template>

<script>
import { fetchBoard } from '@/api/boards'

export default {
  props: {
    listItem: {
      type: Object,
      required: true,
    },
  },
  // methods: {
  //   detail() {
  //     this.$router.push({
  //       path: '/board/detail',
  //     })
  //   },
  // },
}
</script>
