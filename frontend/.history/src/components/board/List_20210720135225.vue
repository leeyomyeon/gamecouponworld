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
                <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
                </tr>
                <tr>
                <th scope="row">2</th>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>@fat</td>
                </tr>
                <tr>
                <th scope="row">3</th>
                <td colspan="2">Larry the Bird</td>
                <td>@twitter</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
export default {
    
}
</script>