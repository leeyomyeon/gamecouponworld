package com.ssafy.backend.comment.model;

public class CommentModel {
	int boardID;
	int feedID;
	int commentID;
	int parentComment;
	String content;
	String userID;
	String date;
	int recommendCnt;
	
	public int getBoardID() {
		return boardID;
	}
	public void setBoardID(int boardID) {
		this.boardID = boardID;
	}
	public int getFeedID() {
		return feedID;
	}
	public void setFeedID(int feedID) {
		this.feedID = feedID;
	}
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public int getParentComment() {
		return parentComment;
	}
	public void setParentComment(int parentComment) {
		this.parentComment = parentComment;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getRecommendCnt() {
		return recommendCnt;
	}
	public void setRecommendCnt(int recommendCnt) {
		this.recommendCnt = recommendCnt;
	}
	
}
