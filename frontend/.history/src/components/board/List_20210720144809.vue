<template>
    <div>
        <h1>게시판 목록</h1>
        <table class="table">
            <thead>
                <tr>
                <th scope="col">글 번호</th>
                <th scope="col">카테고리</th>
                <th scope="col">제목</th>
                <th scope="col">닉네임</th>
                <th scope="col">날짜</th>
                <th scope="col">조회수</th>
                <th scope="col">추천수</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="value in data">
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import data from '@/data'
export default {
    name: 'List',
    data() {
        return {
            data: data,
        }
    }
}
</script>