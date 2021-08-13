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
          <BoardListItem />
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import { fetchBoards, CountBoard } from '@/api/boards'
import BoardListItem from '@/components/board/BoardListItem.vue'

export default {
  components: { BoardListItem },
  data() {
    return {
      boardList: '',
    }
  },
  methods: {
    setup() {
      console.log(hihih)
      const totalBoardCount = CountBoard()
      console.log(totalBoardCount)
      // LIMIT 행 갯수 OFFSET 시작 행
      const boardList = fetchBoards(totalBoardCount, 1)
      this.boardList = boardList
      console.log(boardList)
      // this.requestFollowList = res.data
    },
    write() {
      this.$router.push({
        path: '/board/create',
      })
    },
  },
}
</script>
