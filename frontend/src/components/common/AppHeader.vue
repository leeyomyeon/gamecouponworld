<template>
  <header>
    <nav>
      <div id="menuToggle">
        <input @click="offUser" type="checkbox" id="testInput" />
        <span></span>
        <span></span>
        <span></span>
        <ul @click="offMenu" class="clickIcon" id="menu">
          <li>
            <router-link class="link" to="/main">
              <div style="padding-left: 0.3em;">
                <i class="fas fa-download"></i>
              </div>
            </router-link>
          </li>
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
        <div @click="offUser">
          <SearchUserForm v-if="isUserLogin"></SearchUserForm>
        </div>
        <div @click="offUser">
          <Notice v-if="isUserLogin"></Notice>
        </div>
        <span @click="openUser" style="color: white; padding-left: 0.6rem;">
          <i class="fas fa-user fa-lg"></i>
          <div v-if="isOpen" class="modal">
            <div class="dialog">
              <div v-if="!isUserLogin" style="padding: 0 0.2em;">
                <router-link
                  to="/user/confirmEmail"
                  style="text-decoration: none;color:gray;font-weight:bold;margin-bottom:0.1em;"
                  >회원가입</router-link
                >
                <router-link
                  to="/account/login"
                  style="flex-wrap: wrap;text-decoration: none;color:gray;font-weight:bold;margin-bottom:0.1em;"
                  >로그인</router-link
                >
              </div>
              <a
                v-if="isUserLogin"
                href="javascript:;"
                @click="logoutUser"
                style="text-decoration: none;color:gray;font-weight:bold;margin-bottom:0.1em;"
                >로그아웃</a
              >
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
import Notice from '@/components/common/Notice'
export default {
  components: {
    SearchUserForm,
    Notice,
  },
  data() {
    return {
      isOpen: false,
    }
  },
  computed: {
    isUserLogin() {
      return this.$store.state.userID != ''
    },
  },
  methods: {
    logoutUser() {
      this.$store.commit('clearUserid')
      this.$store.commit('clearToken')
      deleteCookie('one_auth')
      deleteCookie('one_user')
      this.$router.push('/account/login')
      sessionStorage.removeItem('userID')
    },
    offUser() {
      if (this.isOpen == true) {
        this.isOpen = false
      }
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
