<template>
  <div>
    <h3>댓글 작성 Form</h3>
    <div class="d-flex">
      <input
        type="text"
        @keyup.enter="onSubmit"
        v-model="userComment"
        class="form-control"
        autofocus
      />
      <button @click="onSubmit" class="btn btn-primary">등록</button>
    </div>
    <ul>
      <li v-for="comment in commentList" :key="comment.commentID">
        {{ comment.content }}
        <button @click="deleteCommentConfirm">X</button>
      </li>
    </ul>
  </div>
</template>

<script>
import { createComment, detailComment, deleteComment } from '@/api/boards.js'
import { getUserFromCookie } from '@/utils/cookies.js'

export default {
  data() {
    return {
      userComment: '',
      boardID: this.$route.params.id,
      userID: getUserFromCookie(),
      commentList: '',
    }
  },
  created() {
    this.listUpdate()
  },
  methods: {
    async listUpdate() {
      const commentList = await detailComment(this.boardID)
      this.commentList = commentList.data
      console.log(this.commentList)
    },
    onSubmit() {
      if (this.userComment.length > 200) {
        this.userComment = ''
        console.log('200자가 넘는 댓글은 작성하실 수 없습니다')
      } else {
        console.log(`댓글 내용 ${this.userComment}`)
        const boardData = {
          content: this.userComment,
          boardID: this.boardID,
          userID: this.userID,
        }
        console.log(boardData)
        createComment(boardData)
          .then(() => {
            console.log('댓글저장성공')
            this.userComment = ''
            this.listUpdate()
          })
          .catch(() => {
            console.log('댓글작성실패')
            this.userComment = ''
          })
      }
    },
    deleteCommentConfirm() {
      deleteComment(this.key)
        .then() {
          console.log('삭제성공')
          this.listUpdate()
        }
        .catch(){
          console.log('삭제실패')
        }

    },
  },
}
</script>
