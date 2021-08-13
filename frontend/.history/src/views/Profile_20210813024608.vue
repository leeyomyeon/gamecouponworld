!<template>
  <div>
    <ProfileBar />
    <Profile-info />
  </div>
</template>

<script>
import ProfileInfo from '../components/user/ProfileInfo.vue'
import ProfileBar from '@/components/user/ProfileBar.vue'
export default {
  components: { ProfileInfo, ProfileBar },
}
</script>

<style></style>
