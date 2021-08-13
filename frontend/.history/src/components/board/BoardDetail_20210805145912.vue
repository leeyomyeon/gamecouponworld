<template>
  <div>
    <h2>상세페이지</h2>
    <tr>
      <th>제목 :</th>
      <td>{{ detail.title }}</td>
    </tr>
    <tr>
      <td>작성자 :</td>
      <td>{{ detail.writer }}</td>
    </tr>
    <tr>
      <td>내용 :</td>
      <td>{{ detail.content }}</td>
    </tr>
    <button @click="$router.push('/board')">목록</button>
    <div v-if="userID == `${detail.writer}`">
      <button @click="deleteBoardData">삭제하기</button>
      <button @click="move">수정하기</button>
    </div>
  </div>
</template>

<script>
import { fetchBoard, deleteBoard, viewUp } from '@/api/boards.js'
import { getUserFromCookie } from '@/utils/cookies.js'
export default {
  data() {
    return {
      detail: [],
      userID: getUserFromCookie(),
      boardID: this.$route.params.id,
      view: 0,
    }
  },
  async created() {
    const view = await viewUp(this.boardID)
    this.detail = view.data
    this.view = view.data.viewCount
    console.log('조회수')
    console.log(this.view)
  },
  methods: {
    async deleteBoardData() {
      if (confirm('정말로 삭제하시겠습니까?')) {
        const temp2 = this.$route.params.id
        await deleteBoard(temp2)
        alert('글 삭제 완료~!')
        this.$router.push('/board')
      }
    },
    move() {
      this.$router.push(`/board/edit/${this.boardID}`)
    },
  },
}
</script>
