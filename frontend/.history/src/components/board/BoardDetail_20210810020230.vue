<template>
  <div>
    <div class="board-top">
      <span>
        <h2 class="board-title">
          <strong>[쿠키런 킹덤]</strong>
          {{ detail.title }}
        </h2>
      </span>
      <span>
        <em class="writer"> {{ detail.writer }}</em>
        <em> | 조회수 {{ detail.viewCount }} | {{ detail.date }}</em>
      </span>
    </div>
    <div class="board-body">
      <span class=":">
        {{ detail.content }}
      </span>
    </div>
    <div class="btn-list">
      <button class="btn-primary" @click="$router.push('/board')">목록</button>
      <div v-if="userID == `${detail.writer}`">
        <button class="btn-primary" @click="deleteBoardData">삭제하기</button>
        <button class="btn-primary" @click="move">수정하기</button>
      </div>
    </div>
  </div>
</template>

<script>
import { deleteBoard, viewUp } from '@/api/boards.js'
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

<style scoped src="../css/board/boardDetail.css"></style>
