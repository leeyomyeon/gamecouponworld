<template>
  <div class="box">
    <form @submit.prevent="submitForm">
      <div class="container">
        <div v-if="RepreCheck">
          <img
            @click="eventRepreInput"
            src="../../assets/reprePhoto.jpg"
            style="width: 22vw; height: 22vw; padding: 0 1vw;padding-bottom: 1vw;"
          />
          <input type="file" id="reprePhoto" @change="isInputReprePhoto" />
        </div>
        <div v-if="!RepreCheck">
          <img
            :src="repreImage"
            style="width: 22vw; height: 22vw; padding: 0 1vw; padding-bottom: 1vw;"
          />
        </div>
        <div v-for="plusImage in plusImages" :key="plusImage.id">
          <img
            :src="plusImage"
            style="width: 22vw; height: 22vw; padding: 0 1vw;padding-bottom: 1vw;"
          />
        </div>
        <div v-if="!RepreCheck">
          <img
            @click="eventPlusInput"
            src="../../assets/plusPhoto.jpg"
            style="width: 21vw; height: 21vw;"
          />
          <input
            type="file"
            id="photos"
            multiple="multiple"
            @change="isInputPlusPhoto"
          />
        </div>
      </div>
      <hr style="margin-top: 0; color: #e87c03; height:0.2rem;" />
      <div class="input-box">
        <textarea
          id="content"
          type="text"
          placeholder=" "
          spellcheck="false"
          autocomplete="off"
          v-model="content"
        />
        <label for="content">내용</label>
      </div>
      <button
        :disabled="!repreImage || !content"
        style="width: 100%; height:2em; padding:0;"
        type="submit"
      >
        올리기
      </button>
    </form>
  </div>
</template>

<script>
import { createFeed } from '@/api/feed.js'
export default {
  data() {
    return {
      plusImages: [],
      repreImage: '',
      RepreCheck: true,
      content: '',
      formData: new FormData(),
    }
  },
  methods: {
    isInputReprePhoto(event) {
      this.RepreCheck = false
      // 사진 보여주기위해서 넣어줘
      this.repreImage = URL.createObjectURL(event.target.files[0])
      // 보낼거 저장해놓자
      const fileElement = document.querySelector('#reprePhoto')
      this.formData.append('files', fileElement.files[0])
    },
    isInputPlusPhoto(event) {
      // 사진 보여주기위해서 넣어줘
      for (var i of event.target.files) {
        this.plusImages.push(URL.createObjectURL(i))
      }
      // 보낼거 저장해놓자
      const filesElement = document.querySelector('#photos')
      for (let i = 0; i < filesElement.files.length; i++) {
        this.formData.append('files', filesElement.files[i])
      }
    },
    eventRepreInput() {
      document.getElementById('reprePhoto').click()
    },
    eventPlusInput() {
      document.getElementById('photos').click()
    },
    async submitForm() {
      try {
        this.formData.append('userID', this.$store.state.userID)
        this.formData.append('content', this.content)
        await createFeed(this.formData)
        // 해당 detailpage로 이동
        this.$router.push('/feed')
      } catch (error) {
        console.log(error)
      }
    },
  },
}
</script>

<style scoped src="../../components/css/user/default.css"></style>
<style scoped src="../../components/css/feed/feedCreate.css"></style>
<style scoped></style>
