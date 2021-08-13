package com.ssafy.backend.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.backend.social.mapper.SocialMapper;
import com.ssafy.backend.social.model.SocialModel;

@Service
public class SocialServiceImpl implements SocialService{

	@Autowired
	SocialMapper mapper;
	
	@Override
	public List<String> findFollower(String userid) {
		return mapper.findFollower(userid);
	}

	@Override
	public List<String> findFollowing(String userid) {
		return mapper.findFollowing(userid);
	}

	@Override
	public int requestFollow(SocialModel model) {
		return mapper.requestFollow(model);
	}

	@Override
	public int allowFollow(SocialModel model) {
		return mapper.allowFollow(model);
	}

	@Override
	public List<String> findrequestFollow(String userid) {
		return mapper.findrequestFollow(userid);
	}

	@Override
	public SocialModel checkDuplicate(SocialModel model) {
		return mapper.checkDuplicate(model);
	}

	@Override
	public int cancleFollow(SocialModel model) {
		return mapper.cancleFollow(model);
	}

	@Override
	public SocialModel findrequestFollowOne(SocialModel model) {
		return mapper.findrequestFollowOne(model);
	}

	@Override
	public int canclerequestFollow(SocialModel model) {
		return mapper.canclerequestFollow(model);
	}

	@Override
	public List<SocialModel> findAlarm(String targetID) {
		// TODO Auto-generated method stub
		return mapper.findAlaram(targetID);
	}

	@Override
	public int readAlarm(SocialModel model) {
		return mapper.readAlarm(model);
	}

}
