<template>
  <div>
    <h1>자유 게시판</h1>
    <!-- 모든 게시글을 보여주는 컴포넌트 -->
    <ul>
      <BoardListItem
        v-for="(boardItem, index) in boardItems"
        :key="index"
        :boardItem="boardItem"
      ></BoardListItem>
    </ul>

    <!-- 페이지네이션  -->
    <!-- 검색  -->
  </div>
</template>

<script>
import BoardListItem from '@/components/board/BoardListItem'
import { fetchBoards } from '@/api/boards'
export default {
  components: {
    BoardListItem,
  },
  data() {
    return {
      boardItems: [],
    }
  },
  methods: {
    async fetchData(initLimit, initOffset) {
      const params = {
        limit: initLimit,
        offset: initOffset,
      }
      const response = await fetchBoards(params)
      console.log(response)
      // 응답받은 것 this.boardItems에 넣는다.
    },
  },
  created() {
    // 초기값 1부터 5개 보여주라
    this.fetchData(5, 0)
  },
}
</script>

<style></style>
