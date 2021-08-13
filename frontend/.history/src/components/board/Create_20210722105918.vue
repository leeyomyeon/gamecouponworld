<template>
  <div>
    <h1>Create page</h1>
    <fieldset class="p-4">
      <div class="mb-3">
        <input
          v-model="title"
          type="text"
          class="form-control"
          id="title"
          placeholder="제목을 입력해 주세요"
        />
      </div>
      <div class="mb-3">
        <textarea
          v-model="content"
          class="form-control"
          id="content"
          rows="3"
        ></textarea>
      </div>
      <div class="d-flex justify-content-end">
        <button @onclick="write" type="submit" class="btn btn-light">
          등 록
        </button>
      </div>
    </fieldset>
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
  methods: {
    write() {},
  },
}
</script>
