<template>
  <div>
    <h1>리스트 목록</h1>
    <!-- <tr :key="idx" v-for="(value, idx) in data" @click="detail(idx)">
      <th scope="row">{{ value.order }}</th>
      <td>{{ value.category }}</td>
      <td>{{ value.title }}</td>
      <td>{{ value.writer }}</td>
      <td>{{ value.date }}</td>
      <td>{{ value.views }}</td>
      <td>{{ value.recommends }}</td>
    </tr> -->
  </div>
</template>

<script>
import { fetchBoard } from '@/api/boards'

export default {
  props: {
    todo: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      boardID: list.id,
    }
  },
  async created() {
    // 특정 게시물 정보
    const boardList = await fetchBoard(this.boardID)
    this.boardList = boardList
    console.log(boardList)
  },
}
</script>
