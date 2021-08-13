package com.ssafy.backend.user.service;

import com.ssafy.backend.user.model.UserModel;

public interface LoginService {
    UserModel login(UserModel model) throws Exception;
    
    UserModel userInfo(String id) throws Exception;
}
