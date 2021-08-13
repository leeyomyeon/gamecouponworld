<template>
  <div>
    <h3>댓글 작성 Form</h3>
    <div class="d-flex">
      <input
        type="text"
        @keyup.enter="onSubmit"
        v-model="content"
        class="form-control"
        autofocus
      />
      <button @click="onSubmit" class="btn btn-primary">등록</button>
    </div>
  </div>
</template>

<script>
import { createComment } from '@/api/boards.js'
import { getUserFromCookie } from '@/utils/cookies.js'

export default {
  data() {
    return {
      content: '',
      boardID: this.$route.params.id,
      userID: getUserFromCookie(),
    }
  },
  methods: {
    onSubmit() {
      if (this.content.length > 200) {
        this.content = ''
        console.log('200자가 넘는 댓글은 작성하실 수 없습니다')
      } else {
        console.log(`댓글 내용 ${this.content}`)
        const boardData = {
          content: this.content,
          boardID: this.boardID,
          userID: this.userID,
        }
        createComment(boardData)
          .then(() => {
            console.log('댓글저장성공')
            this.content = ''
          })
          .catch(() => {
            console.log('댓글작성실패')
            this.content = ''
          })
      }
    },
  },
}
</script>
