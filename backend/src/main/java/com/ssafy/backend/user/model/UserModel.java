package com.ssafy.backend.user.model;

public class UserModel {
	String userID;		// 아이디
	String userName;	// 이름
	String password;	// 비밀번호
	String email;		// 이메일
	String alias;		// 별명
	String profilePath;	// 프로필사진 경로
	String bio;			// 자기소개
	int likeCnt;
	
	public String getUserID() {
		return userID;
	}
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getProfilePath() {
		return profilePath;
	}
	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	@Override
	public String toString() {
		return "UserModel [userID=" + userID + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", alias=" + alias + ", profilePath=" + profilePath + ", bio=" + bio + "]";
	}
	
	
}
