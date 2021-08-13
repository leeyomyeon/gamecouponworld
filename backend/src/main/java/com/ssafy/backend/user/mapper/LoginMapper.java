package com.ssafy.backend.user.mapper;

import org.springframework.stereotype.Repository;

import com.ssafy.backend.user.model.UserModel;

@Repository
public interface LoginMapper {
    UserModel login(UserModel model) throws Exception;
    
    UserModel userInfo(String id) throws Exception;
}
