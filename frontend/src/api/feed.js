import { instance } from './index'

// 피드 데이터 목록을 조회하는 API
function fetchFeeds() {
  return instance.post('/feed/mainFeedList')
}

// 특정 피드를 조회하는 API
function fetchFeed(id) {
  return instance.get(`/feed/select/${id}`)
}

// 피드 작성 API
function createFeed(data) {
  return instance.post('feed/write', data)
}

// 피드 수정 API
function updateFeed(data) {
  return instance.put('feed/update', data)
}

// 피드 삭제 API
function deleteFeed(userID, feedID) {
  return instance.delete(`feed/delete/${userID}/${feedID}`)
}

// 좋아요 누른 유저인지 Check 하는 API
function isUserLike(data) {
  return instance.post('feed/likeFeedList', data)
}

// 피드 좋아요 증가 API
function likeFeed(data) {
  return instance.post('feed/incLikeCnt', data)
}

// 피드 좋아요 감소 API
function unlikeFeed(data) {
  return instance.post('feed/decLikeCnt', data)
}
// 특정 유저의 피드 불러오기
function getFeedList(data) {
  return instance.post('feed/getFeedList', data)
}
//피드 댓글 등록하기
function registerFeedComment(data) {
  return instance.post('comment/registFeed', data)
}
//피드 댓글 불러오기
function getFeedComment(feedID) {
  return instance.get(`comment/getFeed/${feedID}`)
}

export {
  fetchFeeds,
  fetchFeed,
  createFeed,
  updateFeed,
  deleteFeed,
  isUserLike,
  likeFeed,
  unlikeFeed,
  getFeedList,
  getFeedComment,
  registerFeedComment,
}
