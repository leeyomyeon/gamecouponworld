<template>
  <div>
    <h1>Create page</h1>
    <fieldset class="p-4">
      <div class="mb-3">
        <input
          v-model="title"
          type="text"
          class="form-control"
          id="title"
          placeholder="제목을 입력해 주세요"
        />
      </div>
      <div class="mb-3">
        <textarea
          v-model="content"
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
import data from '@/data'
import { createBoard } from '@/api/board'

export default {
  name: 'Create',
  data() {
    return {
      data: data,
      title: '',
      content: '',
      views: '',
      recommends: '',
      date: '',
    }
  },

  methods: {
    async write() {
      // getDate 함수가 보정이 필요해서 ___ 코드 고쳐야함
      const today = new Date()
      const date_today =
        today.getFullYear() +
        '-' +
        (today.getMonth() + 1) +
        '-' +
        today.getDate()
        
      const boardData= [
        // 글 번호, 카테고리, 닉네임, 날짜, 추천 수, 조회수 등록 필요
        title: this.title,
        content: this.content,
        date: date_today,
        views: 0,
        recommends: 0,
      ]
      this.$axios.post('/api/boards/post', this.board)
      .then (
        this.$router.push({
          path: '/board/list',
        })
      )
      .catch((ex) => {
          console.error("글 작성 실패")
      })
    },
  },
}
</script>
