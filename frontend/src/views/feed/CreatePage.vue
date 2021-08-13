<template>
  <div>
    <FeedAppbar />
    <FeedCreateForm />
  </div>
</template>

<script>
import FeedCreateForm from '@/components/feed/FeedCreateForm'
import FeedAppbar from '@/components/feed/FeedAppbar.vue'
export default {
  components: {
    FeedCreateForm,
    FeedAppbar,
  },
}
</script>

<style></style>
