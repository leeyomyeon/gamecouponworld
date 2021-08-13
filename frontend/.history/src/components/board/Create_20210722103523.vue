<template>
  <div>
    <h1>Create page</h1>
    <div>
      <input v-model="title" placeholder="제목을 입력해주세요" />
      <input v-model="content" placeholder="내용을 입력해주세요" />
      <button @click="write">등 록</button>
    </div>
    <div class="mb-3">
      <input
        type="text"
        class="form-control"
        id="title"
        placeholder="제목을 입력해 주세요"
      />
    </div>
    <div class="mb-3">
      <textarea class="form-control" id="content" rows="3"></textarea>
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
