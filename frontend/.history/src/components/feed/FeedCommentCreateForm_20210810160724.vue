<template>
  <div>
    <div class="d-flex btn-submit">
      <input
        type="text"
        @keyup.enter="onSubmit"
        v-model="userComment"
        class="form-control"
        autofocus
      />
      <button @click="onSubmit" class="btn btn-primary">등록</button>
    </div>
    <div class="comment-list">
      <ul>
        <li v-for="(comment, index) in commentList" :key="comment.commentID">
          <div v-if="index === commentList.length - 1">
            <div class="d-flex justify-content-between">
              <div class="user-content">
                <span class="userID">{{ comment.userID }} </span>
                <span>{{ comment.content }}</span>
              </div>
              <button
                v-if="comment.userID === userID"
                class="btn"
                @click="deleteCommentConfirm(comment.commentID)"
              >
                X
              </button>
            </div>
            <em>
              <span>{{ comment.date }}</span>
            </em>
          </div>
          <div v-else>
            <div>
              <div class="profile-photo">
                <!-- <img src="userInfo({{comment.userID}})" alt="" /> -->
              </div>
              <div class="d-flex justify-content-between">
                <div class="user-content">
                  <span class="userID">{{ comment.userID }} </span>
                  <span>{{ comment.content }}</span>
                </div>
                <button
                  v-if="comment.userID === userID"
                  class="btn"
                  @click="deleteCommentConfirm(comment.commentID)"
                >
                  X
                </button>
              </div>
              <em>
                <span>{{ comment.date }}</span>
              </em>
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { deleteComment } from '@/api/boards.js'
import { getFeedComment, registerFeedComment } from '@/api/feed.js'
import { getUserFromCookie } from '@/utils/cookies.js'
import { fetchUser } from '@/api/auth.js'

export default {
  data() {
    return {
      userComment: '',
      feedID: this.$route.params.id,
      userID: getUserFromCookie(),
      commentList: [],
    }
  },
  created() {
    this.listUpdate()
  },
  methods: {
    async listUpdate() {
      const commentList = await getFeedComment(this.feedID)
      console.log(commentList.data)
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
          feedID: this.feedID,
          userID: this.userID,
        }
        console.log(boardData)
        registerFeedComment(boardData)
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
    async userInfo(userID) {
      const Userdata = await fetchUser(userID)
      console.log('hihihihihi')
      console.log(Userdata.data.profilepath)
      return Userdata.data.profilepath
    },
  },
}
</script>

<style scoped src="../css/feed/feedComment.css"></style>
