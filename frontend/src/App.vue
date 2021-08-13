<template>
  <div id="app">
    <AppHeader></AppHeader>
    <div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import AppHeader from '@/components/common/AppHeader.vue'

export default {
  name: 'App',
  components: {
    AppHeader,
  },
}
</script>

<style></style>
