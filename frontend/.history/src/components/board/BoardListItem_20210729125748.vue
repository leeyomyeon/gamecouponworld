<template>
  <div>
    <tr
      class="d-none d-sm-block"
      @click="$router.push('board/' + listItem.boardID)"
    >
      <th scope="row">{{ listItem.boardID }}</th>
      <td>쿠키런 킹덤</td>
      <td class="text-wrap">{{ listItem.title }}</td>
      <td>{{ listItem.writer }}</td>
      <td>{{ listItem.date }}</td>
      <td>{{ listItem.viewCount }}</td>
      <td>{{ listItem.recommend }}</td>
    </tr>
    <tr
      class="d-block d-sm-none"
      @click="$router.push('board/' + listItem.boardID)"
    >
      <th scope="row" style="color:blue; margin: 0 3;">[쿠키런 킹덤]</th>
      <td>{{ listItem.title }}</td>
      <td style="color:gray; font-size:small;" class="row">
        <div>
          <span class="">{{ listItem.writer }}</span> |
          <span>조회 : {{ listItem.viewCount }}</span> |
          <span>추천 : {{ listItem.recommend }}</span> |
          <span>{{ listItem.date }}</span>
        </div>
      </td>
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
