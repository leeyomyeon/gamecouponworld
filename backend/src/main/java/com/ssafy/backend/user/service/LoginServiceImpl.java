package com.ssafy.backend.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.backend.user.mapper.LoginMapper;
import com.ssafy.backend.user.model.UserModel;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginMapper mapper;

    // 로그인
    public UserModel login(UserModel model) throws Exception {
        if(model.getUserID() == null || model.getPassword() == null) {
            return null;
        }
        return mapper.login(model);
    }
    
    
    // 사용자 정보
    public UserModel userInfo(String id) throws Exception {
        return mapper.userInfo(id);
    }
}
