import { instance } from './index'

// 게시글 가져오는 API
function fetchBoards(params) {
  return instance.get('/board/list', params)
}

// 특정 게시글 가져오는 API
function fetchBoard(boardID) {
  return instance.get(`/board/${boardID}`)
}

// 글쓰기
function writeBoard(boardData) {
  return instance.post('board/write', boardData)
}

// 글 삭제
function deleteBoard(boardID) {
  return instance.delete(`/board/${boardID}`)
}

// 글 수정
function updateBoard(boardData) {
  return instance.put('board/update', boardData)
}

// 글 검색
function searchBoard(boardData) {
  return instance.get(`search/${searchOption}/${value}/${limit}/${offset}`)
}

//
export {
  fetchBoards,
  fetchBoard,
  writeBoard,
  deleteBoard,
  updateBoard,
  searchBoard,
}
