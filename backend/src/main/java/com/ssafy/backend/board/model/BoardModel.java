package com.ssafy.backend.board.model;

public class BoardModel {
	int boardID;
	int noticeOption;
	String title;
	String content;
	String writer;
	String date;
	String photoPath1;
	String photoPath2;
	String photoPath3;
	String photoPath4;
	int recommend;
	int viewCount;
	
	public int getBoardID() {
		return boardID;
	}
	public void setBoardID(int boardID) {
		this.boardID = boardID;
	}
	public int getNoticeOption() {
		return noticeOption;
	}
	public void setNoticeOption(int noticeOption) {
		this.noticeOption = noticeOption;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getcontent() {
		return content;
	}
	public void setcontent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getdate() {
		return date;
	}
	public void setdate(String date) {
		this.date = date;
	}
	public String getphotoPath1() {
		return photoPath1;
	}
	public void setphotoPath1(String photoPath1) {
		this.photoPath1 = photoPath1;
	}
	public String getphotoPath2() {
		return photoPath2;
	}
	public void setphotoPath2(String photoPath2) {
		this.photoPath2 = photoPath2;
	}
	public String getphotoPath3() {
		return photoPath3;
	}
	public void setphotoPath3(String photoPath3) {
		this.photoPath3 = photoPath3;
	}
	public String getphotoPath4() {
		return photoPath4;
	}
	public void setphotoPath4(String photoPath4) {
		this.photoPath4 = photoPath4;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	@Override
	public String toString() {
		return "BoardModel [noticeOption=" + noticeOption + ", title=" + title
				+ ", content=" + content + ", writer=" + writer + ", date=" + date + ", photoPath1=" + photoPath1
				+ ", photoPath2=" + photoPath2 + ", photoPath3=" + photoPath3 + ", photoPath4=" + photoPath4 + "]";
	}
	
	
}
