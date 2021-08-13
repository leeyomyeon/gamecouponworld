<template>
  <div>
    <form @submit.prevent="submitForm">
      <div>
        <label for="reprePhoto">대표사진</label>
        <input type="file" id="reprePhoto" />
      </div>
      <div>
        <label for="photos">쩌리사진들</label>
        <input type="file" id="photos" multiple="multiple" />
      </div>
      <div>
        <label for="content">내용</label>
        <input type="text" id="content" v-model="content" />
      </div>
      <button type="submit">수정하기</button>
    </form>
  </div>
</template>

<script>
import { updateFeed } from '@/api/feed.js'
export default {
  props: {
    feedItem: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      photoPath1: '',
      photoPath2: '',
      content: '',
    }
  },
  methods: {
    async submitForm() {
      try {
        const formData = new FormData()
        formData.append('userID', this.$store.state.userID)
        formData.append('content', this.content)
        const fileElement = document.querySelector('#reprePhoto')
        const filesElement = document.querySelector('#photos')
        // 대표사진 넣기
        formData.append('files', fileElement.files[0])
        // 쩌리사진들 넣기
        for (let i = 0; i < filesElement.files.length; i++) {
          formData.append('files', filesElement.files[i])
        }
        console.log(formData)
        const response = await updateFeed(formData)
        console.log(response)
        // 해당 detailpage로 이동
        this.$router.push(`/feed/${response.feedID}`)
      } catch (error) {
        console.log(error)
      }
    },
  },
  created() {
    this.content = this.feedItem.content
    // feedItem에서 사진 url 가져와서 보여줘야함
  },
}
</script>

<style></style>
