<template>
  <div>
    <h2>상세페이지</h2>
  </div>
</template>

<script>
export default {
  function fetchBoard(boardID) {
    return instance.get(`/board/${boardID}`)
  }
}
</script>
