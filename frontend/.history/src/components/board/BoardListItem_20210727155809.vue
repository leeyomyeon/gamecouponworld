<template>
  <!-- 밑에 @click="detail(idx) -->
  <tr @click="detail()">
    <th scope="row">{{ listItem.boardID }}</th>
    <td>쿠키런 킹덤</td>
    <td>{{ listItem.title }}</td>
    <td>{{ listItem.writer }}</td>
    <td>{{ listItem.date }}</td>
    <td>{{ listItem.viewCount }}</td>
    <td>{{ listItem.recommend }}</td>
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
