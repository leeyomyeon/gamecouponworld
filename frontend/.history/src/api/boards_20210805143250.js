import { instance } from './index'

// 전체 게시글 수
function CountBoard() {
  return instance.get('/board/totalCnt')
}

// 게시글 가져오는 API
function fetchBoards(limit, offset) {
  return instance.get(`/board/list/${limit}/${offset}`)
}

// 특정 게시글 가져오는 API
function fetchBoard(boardID) {
  return instance.get(`/board/select/${boardID}`)
}

// 글쓰기
function createBoard(boardData) {
  return instance.post('board/write', boardData)
}

// 글 삭제
function deleteBoard(boardID) {
  return instance.delete(`/board/delete/${boardID}`)
}

// 글 수정
function updateBoard(boardData) {
  return instance.put('board/update', boardData)
}

// 글 검색
function searchBoard(searchOption, value, limit, offset) {
  return instance.get(
    `board/search/${searchOption}/${value}/${limit}/${offset}`,
  )
}

// 글 조회수 증가 api

// 댓글 등록 --- 게시판 ID를 필수로
function createComment(boardData) {
  return instance.post('comment/registBoard', boardData)
}

// 댓글 불러오기
function detailComment(boardID) {
  return instance.get(`comment/getBoard/${boardID}`)
}

// 댓글 삭제
function deleteComment(commentID) {
  return instance.delete(`comment/deleteComment/${commentID}`)
}

// 댓글 추천
function likeComment(commentID) {
  return instance.post('comment/recommendUp', commentID)
}

function unlikeComment(commentID) {
  return instance.post('comment/recommendDown', commentID)
}

export {
  CountBoard,
  fetchBoards,
  fetchBoard,
  createBoard,
  deleteBoard,
  updateBoard,
  searchBoard,
  createComment,
  detailComment,
  likeComment,
  unlikeComment,
  deleteComment,
}
