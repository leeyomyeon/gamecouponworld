<template>
  <div class="search-bar">
    <input type="text" @keyup.enter="onKeywordEnter" />
  </div>
</template>

<script>
export default {
  name: 'SearchBar',
  methods: {
    async onKeywordEnter(event) {
      const keyword = event.target.value
      console.log(keyword)
    //   const config = {
    //     params: {
    //       part: 'snippet',
    //       type: 'video',
    //       q: keyword,
    //       key: YOUTUBE_API_KEY,
    //     },
    //   }
    //   const response = await axios.get(YOUTUBE_API_URL, config)
    //   const videoList = response.data.items
      this.$emit('on-keyword-enter', boardList)
    // },
  },
}
</script>