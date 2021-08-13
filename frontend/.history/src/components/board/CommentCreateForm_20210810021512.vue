<template>
  <div class="pt-5  comment-box">
    <h3>댓글</h3>
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
    <div class="pt-4">
      <ul>
        <li v-for="(comment, index) in commentList" :key="comment.commentID">
          <div
            v-if="index === commentList.length - 1"
            class="d-flex justify-content-between pt-2 pb-2"
          >
            <div class="ms-3">
              <p>{{ comment.content }}</p>
              <em>
                <span>{{ comment.userID }}</span>
                <span>{{ comment.date }}</span>
              </em>
            </div>
            <button
              class="btn"
              @click="deleteCommentConfirm(comment.commentID)"
            >
              X
            </button>
          </div>
          <div v-else class="d-flex justify-content-between pt-2 pb-2">
            <div class="ms-3">
              <p>{{ comment.content }}</p>
              <em>
                <span>{{ comment.userID }}</span>
                <span>{{ comment.date }}</span>
              </em>
            </div>
            <button
              class="btn"
              @click="deleteCommentConfirm(comment.commentID)"
            >
              X
            </button>
          </div>
        </li>
      </ul>
    </div>
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
    },
    onSubmit() {
      if (this.userComment.length > 200) {
        this.userComment = ''
        console.log('200자가 넘는 댓글은 작성하실 수 없습니다')
      } else if (this.userComment.length === 0) {
        alert('내용을 입력해주세요')
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
    deleteCommentConfirm(commentID) {
      if (confirm('댓글을 삭제하시겠습니까?')) {
        console.log(commentID)
        deleteComment(commentID)
          .then(() => {
            console.log('삭제성공')
            this.listUpdate()
          })
          .catch(() => {
            console.log('삭제실패')
          })
      } else {
        console.log('삭제취소')
      }
    },
  },
}
</script>

<style scoped src="../css/board/comment.css"></style>
