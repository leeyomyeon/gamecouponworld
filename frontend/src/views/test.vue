<template>
  <div>
    <div>
      <p>Upload an image to Firebase:</p>
      <input type="file" @change="previewImage" accept="image/*" />
    </div>

    <div v-if="imageData != null">
      <img class="preview" :src="picture" />
      <br />
      <button @click="onUpload">Upload</button>
    </div>
  </div>
</template>
<script>
import firebase from 'firebase'

export default {
  name: 'Upload',
  data() {
    return {
      imageData: null,
      picture: null,
      uploadValue: 0,
    }
  },
  methods: {
    previewImage(event) {
      console.log(event + ' ' + '여기는 이벤트')
      this.uploadValue = 0
      this.picture = null
      this.imageData = event.target.files[0]
    },

    onUpload() {
      this.picture = null
      console.log(this.imageData)
      const storageRef = firebase
        .storage()
        .ref(`${this.imageData.name}`)
        .put(this.imageData)
      storageRef.on(
        `state_changed`,
        snapshot => {
          this.uploadValue =
            (snapshot.bytesTransferred / snapshot.totalBytes) * 100
        },
        error => {
          console.log(error.message)
        },
        () => {
          this.uploadValue = 100
          storageRef.snapshot.ref.getDownloadURL().then(url => {
            this.picture = url
            console.log(url + ' ' + '여기는 url찍기')
          })
        },
      )
    },
  },
}
</script>
<style scoped="">
img.preview {
  width: 200px;
}
</style>
