package com.ssafy.backend.social.model;

public class SocialModel {
	int followID;
	int type;
	String sourceID;
	String targetID;
	int alarm;
	
	
	public int getAlarm() {
		return alarm;
	}
	public void setAlarm(int alarm) {
		this.alarm = alarm;
	}
	public int getFollowID() {
		return followID;
	}
	public void setFollowID(int followID) {
		this.followID = followID;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getSourceID() {
		return sourceID;
	}
	public void setSourceID(String sourceID) {
		this.sourceID = sourceID;
	}
	public String getTargetID() {
		return targetID;
	}
	public void setTargetID(String targetID) {
		this.targetID = targetID;
	}
	

}
