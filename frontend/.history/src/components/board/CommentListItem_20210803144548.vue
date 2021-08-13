<template>
  <div>
    <h3>댓글 조회</h3>
    <ul>
      <li></li>
    </ul>

    </div>
  </div>
</template>

<script>
import { createComment } from '@/api/boards.js'
export default {
  data() {
    return {
      boardID: this.$route.params.id,
    }
  },  
  created() {

  },
}
</script>
