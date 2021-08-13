<template>
  <div>
    <h1>Create page</h1>
    <div>
      <input v-model="title" placeholder="제목을 입력해주세요" />
      <input v-model="content" placeholder="내용을 입력해주세요" />
    </div>
  </div>
</template>

<script>
import data from '@/data'
export default {
  name: 'Create',
  data() {
    return {
      data: data,
      title: '',
      content: '',
    }
  },
}
</script>
