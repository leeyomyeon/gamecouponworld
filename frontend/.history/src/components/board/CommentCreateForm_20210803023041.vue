<template>
  <div>
    <h3>댓글 작성 Form</h3>
    <input
      type="text"
      @keyup.enter="onSubmit"
      v-model="userInput"
      class="form-control"
      autofocus
    />
    <button @click="onSubmit" class="btn btn-primary">등록</button>
  </div>
</template>

<script>
export default {
  methods: {
    onSubmit() {
      if (this.userInput.length > 50) {
        this.userInput = ''
      }
      this.$store.commit('CREATE_TODO', this.newTodo(this.userInput))
      this.userInput = ''
    },
    newTodo(content) {
      return {
        id: this.$uuid.v1(),
        content,
        completed: false,
      }
    },
  },
}
</script>
