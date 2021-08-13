<template>
  <div>
    <hr />
    <h1 style="text-align :center;">자유 게시판</h1>

    <hr />

    <div class="m-3">
      <div class="d-flex justify-content-end  align-items-center">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="16"
          height="16"
          fill="currentColor"
          class="bi bi-search"
          viewBox="0 0 16 16"
        >
          <path
            d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"
          />
        </svg>
        <SearchBar @on-keyword-enter="onKeywordEnter" class="ps-1" />
      </div>
      <h3>전체 게시물</h3>
      <div class="d-flex justify-content-end">
        <button @click="write" type="button" class="btn btn-light">
          글쓰기
        </button>
      </div>
      <table class="table table-hover">
        <thead class="d-none d-sm-block">
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
      <div class="d-flex justify-content-center">
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
      boardListTotal: [],
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

    const boardListTotal = await fetchBoards(this.boardTotal, 0)
    this.boardListTotal = boardListTotal.data

    const boardList = await fetchBoards(10, 0)
    this.boardList = boardList.data

    console.log(this.boardListTotal)
  },
  methods: {
    write() {
      this.$router.push({
        path: '/board/create',
      })
    },
    onKeywordEnter(boardListTotal, boardTotal) {
      this.boardListTotal = boardListTotal
      this.boardTotal = boardTotal
    },
    paging(clickPage) {
      const startPage = (clickPage - 1) * 10
      // const boardList = await fetchBoards(10, startPage)
      // this.boardList = boardList.data
      this.boardList = this.boardListTotal.slice(startPage, startPage + 10)
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
        this.first_page + pageSymbol * 5 > 0 &&
        (this.first_page + pageSymbol * 5) * 10 <= this.boardTotal
      ) {
        this.first_page = this.first_page + pageSymbol * 5
        this.second_page = this.second_page + pageSymbol * 5
        this.third_page = this.third_page + pageSymbol * 5
        this.forth_page = this.forth_page + pageSymbol * 5
        this.fifth_page = this.fifth_page + pageSymbol * 5
        this.paging(this.first_page)
      }
    },
  },
}
</script>
