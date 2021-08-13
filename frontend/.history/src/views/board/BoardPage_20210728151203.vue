<template>
  <div>
    <h1>자유 게시판</h1>

    <hr />

    <div class="m-5">
      <h3>전체 게시물</h3>
      <button @click="write" type="button" class="btn btn-light">글쓰기</button>
      <SearchBar @on-keyword-enter="onKeywordEnter" />
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
          <BoardListItem
            v-for="listItem in boardList"
            :key="listItem.id"
            :listItem="listItem"
          >
          </BoardListItem>
        </tbody>
      </table>
      <nav aria-label="Page navigation example">
        <ul class="pagination">
          <li
            class="page-item page-link"
            aria-label="Previous"
            @click="nextPaging(-1)"
          >
            <span aria-hidden="true">&laquo;</span>
          </li>
          <li
            v-if="pagingNum(first_page)"
            class="page-item page-link"
            @click="paging(first_page)"
          >
            {{ first_page }}
          </li>
          <li
            v-if="pagingNum(second_page)"
            class="page-item page-link"
            @click="paging(second_page)"
          >
            {{ second_page }}
          </li>
          <li
            v-if="pagingNum(third_page)"
            class="page-item page-link"
            @click="paging(third_page)"
          >
            {{ third_page }}
          </li>
          <li
            v-if="pagingNum(forth_page)"
            class="page-item page-link"
            @click="paging(forth_page)"
          >
            {{ forth_page }}
          </li>
          <li
            v-if="pagingNum(fifth_page)"
            class="page-item page-link"
            @click="paging(fifth_page)"
          >
            {{ fifth_page }}
          </li>
          <li
            class="page-item page-link"
            aria-label="Next"
            @click="nextPaging(1)"
          >
            <span aria-hidden="true">&raquo;</span>
          </li>
        </ul>
      </nav>
    </div>
  </div>
</template>

<script>
import { fetchBoards, CountBoard } from '@/api/boards'
import SearchBar from '@/components/board/SearchBar.vue'
import BoardListItem from '@/components/board/BoardListItem.vue'

export default {
  components: { BoardListItem, SearchBar },
  data() {
    return {
      boardList: [],
      boardTotal: 0,
      first_page: 1,
      second_page: 2,
      third_page: 3,
      forth_page: 4,
      fifth_page: 5,
    }
  },
  async created() {
    const totalBoardCount = await CountBoard()
    this.boardTotal = totalBoardCount.data
    // LIMIT 행 갯수 OFFSET 시작 행
    const boardList = await fetchBoards(10, 0)
    this.boardList = boardList.data
    console.log(this.boardList)
  },
  methods: {
    write() {
      this.$router.push({
        path: '/board/create',
      })
    },
    onKeywordEnter(boardList) {
      this.boardList = boardList
    },
    async paging(clickPage) {
      const startPage = (clickPage - 1) * 10
      const boardList = await fetchBoards(10, startPage)
      this.boardList = boardList.data
      console.log(this.boardList)
    },
    pagingNum(pageNum) {
      if ((pageNum - 1) * 10 < this.boardTotal) {
        return true
      } else {
        return false
      }
    },
    nextPaging(pageSymbol) {
      if (
        this.first_page + pageSymbol * 5 &&
        (this.first_page + pageSymbol * 5 - 1) * 10 > this.boardTotal
      )
        this.first_page = this.first_page + pageSymbol * 5
      this.second_page = this.first_page + pageSymbol * 5
      this.third_page = this.first_page + pageSymbol * 5
      this.forth_page = this.first_page + pageSymbol * 5
      this.fifth_page = this.first_page + pageSymbol * 5
    },
  },
}
</script>
