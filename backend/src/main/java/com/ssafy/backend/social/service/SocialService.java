package com.ssafy.backend.social.service;

import java.util.List;

import com.ssafy.backend.social.model.SocialModel;

public interface SocialService {
	List<String> findFollower(String userid);
	
	List<String> findFollowing(String userid);
	
	int requestFollow(SocialModel model);
	
	int allowFollow(SocialModel model);
	
	List<String> findrequestFollow(String userid);
	
	SocialModel checkDuplicate(SocialModel model);
	
	int cancleFollow(SocialModel model);
	
	SocialModel findrequestFollowOne(SocialModel model);
	
	int canclerequestFollow(SocialModel model);
	
	List<SocialModel> findAlarm(String targetID);

	int readAlarm(SocialModel model);
}

