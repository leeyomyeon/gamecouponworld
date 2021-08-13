<template>
  <button
    type="button"
    class="btn btn-primary"
    data-bs-toggle="modal"
    data-bs-target="#Delete-Modal"
  >
    X
  </button>
  <!-- 삭제 confirm 모달 -->
  <div class="modal" tabindex="-1" id="Delete-Modal">
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
</template>

<script>
export default {
  methods: {},
}
</script>
