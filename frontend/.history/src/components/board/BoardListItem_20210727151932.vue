<template>
  <div>
    <h1>리스트 목록</h1>
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
  </div>
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
  async created() {
    // 특정 게시물 정보
    const boardList = await fetchBoard(this.boardID)
    this.boardList = boardList
    console.log(boardList)
  },
  methods: {
    // detail() {
    //   this.$router.push({
    //     path: '/board/detail',
    //   })
    // },
  },
}
</script>
