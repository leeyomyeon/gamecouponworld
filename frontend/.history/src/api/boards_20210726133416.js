import { instance } from './index'

// 게시글 가져오는 API
function fetchBoards(params) {
  return instance.get('/board/list', params)
}

// 특정 게시글 가져오는 API
function fetchBoard(boardID) {
  return instance.get(`/board/${boardID}`)
}

export { fetchBoards, fetchBoard }
