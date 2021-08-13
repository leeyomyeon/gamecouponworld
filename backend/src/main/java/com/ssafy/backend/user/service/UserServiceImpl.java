package com.ssafy.backend.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.backend.user.mapper.UserMapper;
import com.ssafy.backend.user.model.UserModel;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper mapper; 
	
	
	@Override
	//회원가입 기능
	public int userRegister(UserModel model) {
		return mapper.userRegister(model);
	}


	@Override
	public int userModify(UserModel model) {
		// TODO Auto-generated method stub
		return mapper.userModify(model);
	}


	@Override
	public int userDelete(String userid) {
		// TODO Auto-generated method stub
		return mapper.userDelete(userid);
	}


	@Override
	public UserModel getUserInfo(String userid) {
		// TODO Auto-generated method stub
		return mapper.getUserInfo(userid);
	}
	
	public List<String> searchUserList(String userid) {
		return mapper.searchUserList(userid);
	}


	@Override
	public UserModel getUserInfoByEmail(String email) {
		// TODO Auto-generated method stub
		return mapper.getUserInfoByEmail(email);
	}
	
	@Override
	public int insertGameID(String userID) {
		return mapper.insertGameID(userID);
	}

}
