<template>
  <div>
    <h3>댓글 조회</h3>
    <ul>
      <li v-for="comment in commentList" :key="comment.commentID">
        {{ comment.content }}
        <!-- 삭제 버튼 -->
        <button 
          type="button" 
          class="btn btn-primary" 
          data-bs-toggle="modal" 
          data-bs-target="#exampleModal">X
        </button>
        <!-- 삭제 confirm 모달 -->
        <div class="modal" tabindex="-1">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">삭제하시겠습니까?</h5>
                <button
                  type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                ></button>
              </div>
              <div class="modal-body">
                <p>삭제 시 다시 되돌릴 수 없습니다</p>
              </div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-secondary"
                  data-bs-dismiss="modal"
                >
                  취소
                </button>
                <button type="button" class="btn btn-primary">삭제</button>
              </div>
            </div>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import { detailComment } from '@/api/boards.js'
export default {
  data() {
    return {
      boardID: this.$route.params.id,
      commentList: '',
    }
  },
  async created() {
    const commentList = await detailComment(this.boardID)
    this.commentList = commentList.data
    console.log(this.commnetList)
  },
  method() {
    deleteConfirm() {

    }
  }
}
</script>
