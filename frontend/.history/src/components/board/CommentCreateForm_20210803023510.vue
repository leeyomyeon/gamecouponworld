<template>
  <div class="d-flex">
    <h3>댓글 작성 Form</h3>
    <input
      type="text"
      @keyup.enter="onSubmit"
      v-model="userComment"
      class="form-control"
      autofocus
    />
    <button @click="onSubmit" class="btn btn-primary">등록</button>
  </div>
</template>

<script>
import { createComment } from '@/api/boards.js'

export default {
  data() {
    return {
      userComment: '',
    }
  },
  methods: {
    onSubmit() {
      if (this.userComment.length > 50) {
        this.userComment = ''
      } else {
        createComment(this.userComment)
          .then(() => {
            this.userInput = ''
          })
          .catch(() => {
            console.log('댓글작성실패')
            this.userInput = ''
          })
      }
    },
  },
}
</script>
