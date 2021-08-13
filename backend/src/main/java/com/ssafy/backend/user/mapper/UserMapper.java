package com.ssafy.backend.user.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.backend.user.model.UserModel;

@Repository
public interface UserMapper {
	int userRegister(UserModel model);
	
	int userModify(UserModel model);
	
	int userDelete(String userid);
	
	UserModel getUserInfo(String userid);
	
	List<String> searchUserList(String userid);
	
	UserModel  getUserInfoByEmail(String email);
	
	int insertGameID(String userID);
}
