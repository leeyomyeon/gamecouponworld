<template>
  <div>
    <!-- <tr
      class="d-none d-sm-block"
      @click="$router.push('board/' + listItem.boardID)"
    >
      <th scope="row">{{ listItem.boardID }}</th>
      <td>쿠키런 킹덤</td>
      <td>{{ listItem.title }}</td>
      <td>{{ listItem.writer }}</td>
      <td>{{ listItem.date }}</td>
      <td>{{ listItem.viewCount }}</td>
      <td>{{ listItem.recommend }}</td>
    </tr> -->
    <tr
      class="d-block d-sm-none"
      @click="$router.push('board/' + listItem.boardID)"
    >
      <th scope="row">{{ listItem.boardID }}</th>
      <td>쿠키런 킹덤</td>
      <td>{{ listItem.title }}</td>
      <th scope="row">{{ listItem.writer }}</th>
      <td>{{ listItem.date }}</td>
      <td>{{ listItem.viewCount }}</td>
      <td>{{ listItem.recommend }}</td>
    </tr>
  </div>
</template>

<script>
export default {
  props: {
    listItem: {
      type: Object,
      required: true,
    },
  },
}
</script>
