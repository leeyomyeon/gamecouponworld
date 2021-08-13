<template>
  <div>
    <h1>글쓰기 페이지</h1>
  </div>
  <CreateForm />
</template>

<script>
import CreateForm from '@/components/board/CreateForm.vue'
import CommentCreateForm from '@/components/board/CommentCreateForm.vue'
export default {
  components: CreateForm,
}
</script>
