<template>
  <div>
    <tr
      class="d-none d-sm-block"
      @click="$router.push('board/' + listItem.boardID)"
    >
      <th scope="col">{{ listItem.boardID }}</th>
      <td scope="col">쿠키런 킹덤</td>
      <td scope="col" class="text-wrap">{{ listItem.title }}</td>
      <td scope="col">{{ listItem.writer }}</td>
      <td scope="col">{{ listItem.date }}</td>
      <td scope="col">{{ listItem.viewCount }}</td>
      <td scope="col">{{ listItem.recommend }}</td>
    </tr>
    <tr
      class="d-block d-sm-none"
      @click="$router.push('board/' + listItem.boardID)"
    >
      <div>
        <th scope="row" style="color:blue;" class="pe-2">[쿠키런 킹덤]</th>
        <td>{{ listItem.title }}</td>
      </div>
      <td style="color:gray; font-size:small;" class="row">
        <div>
          <span v-if="listItem.writer">{{ listItem.writer }}</span>
          <span v-else>익명</span> |
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
