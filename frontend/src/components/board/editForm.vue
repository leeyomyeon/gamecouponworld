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
        <button @click="modify" type="submit" class="btn btn-light">
          수 정
        </button>
      </div>
    </fieldset>
  </div>
</template>

<script>
import { updateBoard, fetchBoard } from '@/api/boards'
import { getUserFromCookie } from '@/utils/cookies.js'

export default {
  data() {
    return {
      boardData: {
        title: '',
        content: '',
        boardID: this.$route.params.id,
        writer: getUserFromCookie(),
      },
    }
  },

  methods: {
    async modify() {
      console.log(this.boardData)
      if (this.title === '' || this.content === '') {
        console.log('한 글자도 입력하지 않음')
      }
      await updateBoard(this.boardData)
        .then(() => {
          alert('글 수정 성공~!')
          this.$router.push('/board')
        })
        .catch(() => {
          console.error('글 수정 실패')
        })
    },
  },
  async created() {
    const temp = this.$route.params.id
    const res = await fetchBoard(temp)
    console.log(res)
    this.boardData.title = res.data.title
    this.boardData.content = res.data.content
  },
}
</script>
