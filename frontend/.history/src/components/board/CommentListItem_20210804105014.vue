<template>
  <div>
    <h3>댓글 조회</h3>
    <ul>
      <li v-for="comment in commentList" :key="comment.commentID">
        {{ comment.content }}
        <!-- 삭제 버튼 -->

        <button class="btn" @click="showModal = true">Show</button>
        <modal v-if="showModal" @close="showModal = false" name="Umesh">
          <p>X</p>
        </modal>
        <!-- 삭제 confirm 모달 -->
        <div class="modal fade in modal-active">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" @click="$emit('close')" class="close">
                  <span>&times;</span>
                </button>
                <h4 class="modal-title">
                  정말 삭제하시겠습니까?
                </h4>
              </div>
              <div class="modal-body">
                <slot></slot>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default">
                  Close
                </button>
                <button type="button" class="btn btn-primary">
                  Save changes
                </button>
              </div>
            </div>
          </div>
        </div>
        <!-- modal -->
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
      showModal: false,
    }
  },
  created() {
    this.listUpdate()
  },
  method: {
    async listUpdate() {
      const commentList = await detailComment(this.boardID)
      this.commentList = commentList.data
      console.log(this.commentList)
    },
  },
}
</script>
