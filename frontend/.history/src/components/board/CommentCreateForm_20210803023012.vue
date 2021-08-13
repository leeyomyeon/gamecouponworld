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
    <button @click="onSubmit" class="btn btn-primary">Add</button>
  </div>
</template>

<script>
export default {}
</script>
