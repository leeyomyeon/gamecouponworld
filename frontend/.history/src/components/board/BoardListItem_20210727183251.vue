<template>
  <!-- 밑에 @click="detail(idx) -->
  <tr @click="$router.push('board/'+ listItem.boardID)">
    <th scope="row" >{{ listItem.boardID }}</th>
    <td>쿠키런 킹덤</td>
    <td>{{ listItem.title }}</td>
    <td>{{ listItem.writer }}</td>
    <td>{{ listItem.date }}</td>
    <td>{{ listItem.viewCount }}</td>
    <td>{{ listItem.recommend }}</td>
  </tr>
</template>

<script>
export default {
  props: {
    listItem: {
      type: Object,
      required: true,
    },
  },
  methods: {
    detail() {
      this.$router.push({ name: 'boardDetail', props: true, params: { id: ${ listItem.boardID } }})
    },
  },
}
</script>
