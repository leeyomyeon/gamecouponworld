<template>
  <div>
    <h1>Create page</h1>
    <fieldset class="p-4">
      <div class="mb-3">
        <input
          v-model="boardData.title"
          type="text"
          class="form-control"
          id="title"
          placeholder="제목을 입력해 주세요"
        />
      </div>
      <div class="mb-3">
        <textarea
          v-model="boardData.content"
          class="form-control"
          id="content"
          rows="3"
        ></textarea>
      </div>
      <div class="d-flex justify-content-end">
        <button @click="write" type="submit" class="btn btn-light">
          등 록
        </button>
      </div>
    </fieldset>
  </div>
</template>

<script>
import { createBoard } from '@/api/boards'
import { getUserFromCookie } from '@/utils/cookies.js'

export default {
  data() {
    return {
      boardData: {
        title: '',
        content: '',
        writer: getUserFromCookie(),
      },
    }
  },

  methods: {
    write() {
      if (this.title === '' || this.content === '') {
        console.log('한 글자도 입력하지 않음')
      }
      createBoard(this.boardData)
        .then(() => {
          this.$router.push('/board')
        })
        .catch(() => {
          console.error('글 작성 실패')
        })
    },
  },
}
</script>
