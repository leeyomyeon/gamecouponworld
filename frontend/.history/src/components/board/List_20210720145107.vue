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
                <tr :key="idx" v-for="(value, idx) in data">
                <th scope="row">{{data.order}}</th>
                <td>{{data.category}}</td>
                <td>{{data.title}}</td>
                <td>{{data.writer}}</td>
                <td>{{data.date}}</td>
                <td>{{data.views}}</td>
                <td>{{data.recommends}}</td>
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