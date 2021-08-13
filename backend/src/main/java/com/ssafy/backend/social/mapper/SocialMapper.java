package com.ssafy.backend.social.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.backend.social.model.SocialModel;

@Repository
public interface SocialMapper {

	List<String> findFollower(String userid);
	List<String> findFollowing(String userid);
	int requestFollow(SocialModel model);
	int allowFollow(SocialModel model);
	List<String> findrequestFollow(String userid);
	SocialModel checkDuplicate(SocialModel model);
	int cancleFollow(SocialModel model);
	SocialModel findrequestFollowOne(SocialModel model);
	int canclerequestFollow(SocialModel model);
	List<SocialModel> findAlaram(String targetID);
	int readAlarm(SocialModel model);
	
}
