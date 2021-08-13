<template>
  <div>
    <BoardAppbar />
    <CreateForm />
  </div>
</template>

<script>
import CreateForm from '@/components/board/CreateForm.vue'
import BoardAppbar from '@/components/board/BoardAppbar.vue'
export default {
  components: { CreateForm, BoardAppbar },
}
</script>
