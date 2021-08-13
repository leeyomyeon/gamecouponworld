!<template>
  <div>
    <Profilebar />
    <Profile-info />
  </div>
</template>

<script>
import ProfileInfo from '../components/user/ProfileInfo.vue'
import Profilebar from '@/components/user/Profilebar.vue'
export default {
  components: { ProfileInfo, Profilebar },
}
</script>

<style></style>
