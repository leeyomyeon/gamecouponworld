<template>
  <div>
    <FeedAppbar />

    <div style="margin-top: 15%;">
      <div>
        <div>
          <button
            @click="allFeed"
            :class="{ active: boxCheck, 'no-active': boxCheckTwo }"
            class="menu-button"
          >
            <span>
              <i class="fab fa-buromobelexperte fa-2x"></i>
            </span>
          </button>
          <button
            @click="followFeed"
            :class="{ active: boxCheckTwo, 'no-active': boxCheck }"
            class="menu-button"
          >
            <span>
              <i class="far fa-comment fa-2x"></i>
            </span>
          </button>
          <div class="div-create-button">
            <span @click="moveCreate" class="create-button">
              <i class="far fa-plus-square fa-2x"></i>
            </span>
          </div>
        </div>
      </div>
      <div v-if="boxCheck" class="box">
        <!-- 모든 피드들 뿌려주기 -->
        <FeedListItem
          v-for="feedItem in feedItems"
          :key="feedItem.id"
          :feedItem="feedItem"
        ></FeedListItem>
      </div>
      <!-- 팔로우한 유저 피드 불러오기 -->
      <div v-if="boxCheckTwo" @click="followFeed" class="box">
        <following-feed-item
          v-for="feedItem in followingFeedItems"
          :key="feedItem.id"
          :feedItem="feedItem"
        />
      </div>
    </div>
  </div>
</template>

<script>
import FeedAppbar from '@/components/feed/FeedAppbar.vue'
import FeedListItem from '@/components/feed/FeedListItem.vue'
import { fetchFeeds, isUserLike } from '@/api/feed.js'
import { findFollowing } from '@/api/auth.js'
import { getUserFromCookie } from '@/utils/cookies.js'
import FollowingFeedItem from '../../components/feed/FollowingFeedItem.vue'
export default {
  components: {
    FeedListItem,
    FollowingFeedItem,
    FeedAppbar,
  },
  data() {
    return {
      feedItems: [],
      boxCheck: true,
      boxCheckTwo: false,
      followingFeedItems: [],
      following: [],
    }
  },
  methods: {
    //팔로잉 명단 불러오기
    async findFollowing() {
      const temp = {
        userID: getUserFromCookie(),
      }
      const res = await findFollowing(temp)
      this.following = res.data
    },
    // feeds 불러오기
    async fetchData() {
      const response = await fetchFeeds()
      this.feedItems = response.data
      for (var x in response.data) {
        if (this.following.includes(response.data[x].userID)) {
          this.followingFeedItems.push(response.data[x])
        }
      }
    },
    moveCreate() {
      this.$router.push('/feed/create')
    },
    allFeed() {
      this.boxCheck = true
      this.boxCheckTwo = false
    },
    followFeed() {
      this.boxCheck = false
      this.boxCheckTwo = true
    },
  },
  async created() {
    await this.findFollowing()
    await this.fetchData()
    const userID = this.$store.state.userID
    const { data } = await isUserLike(userID)
    var i = 0
    for (i = 0; i < this.followingFeedItems.length; i++) {
      this.followingFeedItems[i].like = false
    }
    for (i = 0; i < data.length; i++) {
      for (var j = 0; j < this.followingFeedItems.length; j++) {
        if (this.followingFeedItems[j].feedID == data[i]['feedID']) {
          this.followingFeedItems[j].like = true
        }
      }
    }
    console.log('좋아요 눌렀는지 check', this.followingFeedItems)
  },
}
</script>

<style scoped src="../../components/css/user/default.css"></style>
<style scoped src="../../components/css/feed/feedList.css"></style>
<style></style>
