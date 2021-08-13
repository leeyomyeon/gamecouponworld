<template>
  <div>
    <h3>댓글 조회</h3>
    <ul>
      <li v-for="comment in commentList" :key="comment.commentID">
        {{ comment.content }}
        <!-- 삭제 버튼 -->

        <!-- <button
          type="button"
          class="btn btn-primary"
          data-bs-toggle="modal"
          data-bs-target="#Delete-Modal"
        >
          X
        </button> -->
        <!-- 삭제 confirm 모달 -->
        <!-- <div class="modal" tabindex="-1" id="Delete-Modal">
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
                <button type="button" class="btn btn-primary" @click="deleteConfirm">삭제</button>
              </div>
            </div>
          </div>
        </div> -->
        <!-- modal 끝 -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#My-Modal">
          Launch demo modal
        </button>

        <!-- Modal -->
        <div class="modal fade" id="My-Modal" tabindex="-1" aria-labelledby="My-ModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="My-ModalLabel">Modal title</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <!-- 버튼을 누르면 이 메시지가 나옵니다 -->
                <div class="card" style="width: 30rem;">
                  <div class="display-5 text-center">Sign In</div>
                  <form class="p-3">
                    <div class="mb-3">
                      <label for="exampleInputEmail1" class="form-label">Username or email</label>
                      <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                      <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                    </div>
                    <div class="mb-3">
                      <label for="exampleInputPassword1" class="form-label">Password</label>
                      <input type="password" class="form-control" id="exampleInputPassword1">
                    </div>
                    <div class="mb-3 form-check d-flex justify-content-between">
                      <div>
                        <input type="checkbox" class="form-check-input" id="exampleCheck1">
                        <label class="form-check-label" for="exampleCheck1">Remember me</label>
                      </div>
                      <a href="">Forgot your password?</a>
                    </div>
                    <button type="submit" class="btn btn-success w-100">Submit</button>
                  </form>
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
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
  },
}
</script>
