<template>
  <div>
    <h1>게시판 목록</h1>
    <div class="m-5">
      <h3>이번 주 HOT 게시물</h3>
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
          <tr :key="idx" v-for="(value, idx) in data">
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
import data from '@/data'
export default {
  name: 'List',
  data() {
    return {
      data: data,
    }
  },
  methods: {
    write() {
      this.$router.push({
        path: '/board/create',
      })
    },
    detail(idx) {
      console.log('ㅎㅇ')
      console.log(idx)
      this.$router.push({
        name: 'Detail', // 특정 데이터만 params로 넘김
        params: {
          board_idx: idx,
        },
      })
    },
  },
}
</script>
