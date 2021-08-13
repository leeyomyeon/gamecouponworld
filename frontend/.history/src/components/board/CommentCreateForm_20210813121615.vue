<template>
  <div>
    <div class="comment-box">
      <div class="comment-top">
        <span>댓글</span>
      </div>
      <div class="comment-body">
        <ul>
          <li v-for="(comment, index) in commentList" :key="comment.commentID">
            <div v-if="!comment.parentComment">
              <div
                v-if="index === commentList.length - 1"
                class="d-flex justify-content-between comment-end"
              >
                <div class="ms-3">
                  <p>{{ comment.content }}</p>
                  <em>
                    <span>{{ comment.userID }}</span> |
                    <span>{{ comment.date }}</span>
                  </em>
                </div>
                <div>
                  <button
                    class="btn"
                    @click="pushRecommentBtn(comment.commentID)"
                  >
                    └
                  </button>
                  <button
                    class="btn"
                    @click="deleteCommentConfirm(comment.commentID)"
                  >
                    X
                  </button>
                </div>
              </div>
              <div v-else class="d-flex justify-content-between comment-start">
                <div class="ms-3">
                  <p>{{ comment.content }}</p>
                  <em>
                    <span>{{ comment.userID }}</span> |
                    <span>{{ comment.date }}</span>
                  </em>
                </div>
                <div>
                  <button
                    class="btn"
                    @click="pushRecommentBtn(comment.commentID)"
                  >
                    └
                  </button>
                  <button
                    v-if="userID == comment.userID"
                    class="btn"
                    @click="createRecomment(comment.commentID)"
                  >
                    X
                  </button>
                </div>
              </div>
            </div>
            <div v-for="recomment in recommentList" :key="recomment.commentID">
              {{ recomment.commentID }} {{ comment.parentComment }}
              {{ recomment.content }}
              <div v-if="recomment.commentID == comment.parentComment">
                {{ recomment.content }}
              </div>
            </div>
            <div
              v-if="reCommentNum == comment.commentID"
              class="d-flex comment-btn"
            >
              <input
                type="text"
                @keyup.enter="onSubmit"
                v-model="userComment"
                class="form-control"
                autofocus
              />
              <button
                @click="createRecomment(comment.commentID)"
                class="btn btn-primary"
              >
                등록
              </button>
            </div>
          </li>
        </ul>
      </div>
    </div>
    <div class="d-flex comment-btn">
      <input
        type="text"
        @keyup.enter="onSubmit"
        v-model="userComment"
        class="form-control"
        autofocus
      />
      <button @click="onSubmit" class="btn btn-primary">등록</button>
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
      reCommentNum: -999,
      recommentList: '',
    }
  },
  created() {
    this.listUpdate()
  },
  methods: {
    async listUpdate() {
      const commentList = await detailComment(this.boardID)
      this.commentList = commentList.data
      this.recommentList = commentList.data
    },
    onSubmit() {
      if (this.userComment.length > 200) {
        this.userComment = ''
        console.log('200자가 넘는 댓글은 작성하실 수 없습니다')
      } else if (this.userComment.length === 0) {
        alert('내용을 입력해주세요')
      } else {
        const boardData = {
          content: this.userComment,
          boardID: this.boardID,
          userID: this.userID,
        }
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
    pushRecommentBtn(commentID) {
      this.reComment = true
      this.reCommentNum = commentID
    },
    createRecomment(commentID) {
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
          parentComment: commentID,
        }
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
  },
}
</script>

<style scoped src="../css/board/comment.css"></style>
