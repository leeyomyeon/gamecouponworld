<template>
  <header style="position:sticky;">
    <nav>
      <div id="menuToggle">
        <input type="checkbox" id="testInput" />
        <span></span>
        <span></span>
        <span></span>
        <ul @click="offMenu" class="clickIcon" id="menu">
          <li>
            <router-link class="link" to="/board">
              <div style="padding-left: 0.3em;">
                <i class="fas fa-book"></i>
              </div>
            </router-link>
          </li>
          <li>
            <router-link class="link" to="/feed">
              <div style="padding-left: 0.3em;">
                <i class="fas fa-comment-dots"></i>
              </div>
            </router-link>
          </li>
          <!-- menu에 항목을 추가하실려면 밑에 주석처럼 만들면됩니다. -->
          <!-- <li>
            <router-link class="link" to="/board">피드</router-link>
          </li> -->
        </ul>
      </div>
      <div class="menu-item" style="margin-left: auto;">
        <SearchUserForm></SearchUserForm>
        <span @click="openUser" style="color: white; padding-left: 1rem;">
          <i class="fas fa-user fa-lg"></i>
          <div v-if="isOpen" class="modal">
            <div class="dialog">
              <router-link to="/user/confirmEmail">signup</router-link>
              <router-link to="/account/login">login</router-link>
              <a href="javascript:;" @click="logoutUser">logout</a>
            </div>
          </div>
        </span>
      </div>
    </nav>
  </header>
</template>

<script>
import { deleteCookie } from '@/utils/cookies'
import SearchUserForm from '@/components/common/SearchUserForm.vue'
export default {
  components: {
    SearchUserForm,
  },
  data() {
    return {
      isOpen: false,
    }
  },
  methods: {
    logoutUser() {
      this.$store.commit('clearUserid')
      this.$store.commit('clearToken')
      deleteCookie('one_auth')
      deleteCookie('one_user')
      this.$router.push('/account/login')
    },
    openUser() {
      this.isOpen = !this.isOpen
    },
    offMenu() {
      document.getElementById('testInput').click()
    },
  },
}
</script>

<style scoped src="../css/user/default.css"></style>
<style scoped src="../css/menu.css"></style>
<style scoped></style>
