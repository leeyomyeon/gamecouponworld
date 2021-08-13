<template>
  <div>
    <label for="searchData" @click="offResult('searchData')">
      <span v-show="clickCheck" style="color:white;">
        <i class="fas fa-search fa-lg"></i>
      </span>
    </label>
    <div v-if="!clickCheck" class="modal">
      <div class="dialog">
        <div class="box">
          <div class="container-nowrap">
            <div v-if="isActive">
              <input
                type="text"
                id="searchData"
                autocomplete="off"
                placeholder="userID"
                v-model="searchData"
                @keyup="search"
                style="border-color:#ffa061; color: #ffa061;"
              />
            </div>
            <label for="searchData" @click="offResult('searchData')">
              <span v-show="!clickCheck">
                <i class="fas fa-times-circle fa-lg" style="color:#ffa061;"></i>
              </span>
            </label>
          </div>
          <div v-if="isActive">
            <hr style="margin: 0.5rem 0" />
            <ul>
              <li
                @click="saveCache(result)"
                v-for="result in results"
                :key="result.id"
              >
                {{ result }}
              </li>
              <div v-if="!searchData">
                <p style="font-weight:900; font-size:0.8rem;">최근 검색</p>
                <hr style="margin: 0.3rem 0;" />
                <li
                  class="list"
                  v-for="currentResult in currentResults"
                  :key="currentResult.id"
                >
                  <div>
                    <span @click="moveProfile(currentResult)">
                      {{ currentResult }}
                    </span>
                    <button @click="deleteCache(currentResult)">
                      <i class="fas fa-user-times"></i>
                    </button>
                  </div>
                  <hr style="margin: 0.3rem 0;" />
                </li>
              </div>
              <li v-if="logMessage && searchData">
                {{ logMessage }}
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { searchUser } from '@/api/auth'
import { UsernameValid } from '@/utils/validation'

export default {
  data() {
    return {
      searchData: '',
      results: [],
      logMessage: '',
      // display
      currentResults: [],
      isActive: false,
      clickCheck: true,
    }
  },
  methods: {
    async search() {
      try {
        if (!UsernameValid(this.searchData) || this.searchData.trim() === '') {
          this.results = []
          this.logMessage = '해당 유저가 없습니다.'
          return
        }
        const { data } = await searchUser(this.searchData)
        this.results = data
        this.resultsDisplay = true
        this.logMessage = ''
      } catch (error) {
        this.results = []
        this.logMessage = '해당 유저가 없습니다.'
        this.resultsDisplay = true
      }
    },
    offResult(id) {
      this.clickCheck = !this.clickCheck
      this.isActive = !this.isActive
      this[`${id}`] = ''
      this.results = []
      this.logMessage = ''
      this.resultsDisplay = !this.resultsDisplay
      this.getCache()
    },
    saveCache(result) {
      // 캐시추가
      const cacheName = this.$store.state.userID
      caches.open(cacheName).then(cache => {
        cache.add(result)
      })
      this.getCache()
      this.moveProfile(result)
    },
    moveProfile(result) {
      this.clickCheck = true
      this.isActive = false
      this.$router.push(`/user/profile/${result}`).catch(() => {})
    },
    getCache() {
      // 캐시가져오기
      const cacheName = this.$store.state.userID
      caches.open(cacheName).then(cache => {
        cache.matchAll().then(responses => {
          for (let response of responses) {
            const findIndex = response['url'].lastIndexOf('/') + 1
            const temp = response['url'].substring(findIndex)
            if (!this.currentResults.includes(temp)) {
              this.currentResults.unshift(temp)
            }
          }
        })
      })
    },
    // 캐시 삭제
    deleteCache(currentResult) {
      const cacheName = this.$store.state.userID
      caches.open(cacheName).then(cache => {
        cache.matchAll().then(responses => {
          for (let response of responses) {
            if (response['url'].includes(currentResult)) {
              cache.delete(response['url'])
              const findIndex = response['url'].lastIndexOf('/') + 1
              const temp = response['url'].substring(findIndex)
              this.currentResults.splice(this.currentResults.indexOf(temp), 1)
            }
          }
        })
      })
    },
  },
}
</script>

<style scoped src="../css/user/default.css"></style>
<style scoped src="../css/user/search.css"></style>
<style scoped></style>
