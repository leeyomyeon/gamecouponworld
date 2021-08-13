package com.ssafy.backend.user.service;

import java.util.List;

import com.ssafy.backend.user.model.UserModel;

public interface UserService {
	int userRegister(UserModel model);
	
	int userModify(UserModel model);
	
	int userDelete(String userid);
	
	UserModel getUserInfo(String userid);
	
	List<String> searchUserList(String userid);
	
	UserModel  getUserInfoByEmail(String email);
	
	int insertGameID(String userID);
}
