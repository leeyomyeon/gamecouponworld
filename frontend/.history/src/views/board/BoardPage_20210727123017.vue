<template>
  <div>
    <h1>자유 게시판</h1>

    <hr />

    <div class="m-5">
      <h3>전체 게시물</h3>
      <button @click="write" type="button" class="btn btn-light">글쓰기</button>
      <table class="table table-hover">
        <thead>
          <tr>
            <th scope="col">글 번호</th>
            <th scope="col">카테고리</th>
            <th scope="col">제목</th>
            <th scope="col">닉네임</th>
            <th scope="col">날짜</th>
            <th scope="col">조회수</th>
            <th scope="col">추천수</th>
          </tr>
        </thead>
        <tbody>
          <tr :key="idx" v-for="(value, idx) in data" @click="detail(idx)">
            <th scope="row">{{ value.order }}</th>
            <td>{{ value.category }}</td>
            <td>{{ value.title }}</td>
            <td>{{ value.writer }}</td>
            <td>{{ value.date }}</td>
            <td>{{ value.views }}</td>
            <td>{{ value.recommends }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import { fetchBoards } from '@/api/boards'

function fetchBoards(limit, offset) {
  return instance.get(`/board/list/${limit}/${offset}`)
}
export default {
  methods: {
    write() {
      this.$router.push({
        path: '/board/create',
      })
    },
  },
  setup() {
    const boardList = await fetchBoards(1, 2)
    console.log(boardList)
    // this.requestFollowList = res.data
  }
}
</script>
