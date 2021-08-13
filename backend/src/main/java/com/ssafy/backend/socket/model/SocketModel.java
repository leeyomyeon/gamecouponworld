package com.ssafy.backend.socket.model;

public class SocketModel {
	String targetID;
	String sourceID;
	public String getTargetID() {
		return targetID;
	}
	public void setTargetID(String targetID) {
		this.targetID = targetID;
	}
	public String getSourceID() {
		return sourceID;
	}
	public void setSourceID(String sourceID) {
		this.sourceID = sourceID;
	}
	public SocketModel(String targetID, String sourceID) {
		super();
		this.targetID = targetID;
		this.sourceID = sourceID;
	}
	

}
