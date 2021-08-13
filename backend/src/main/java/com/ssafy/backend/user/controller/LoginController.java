package com.ssafy.backend.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.backend.user.model.UserModel;
import com.ssafy.backend.user.service.JwtServiceImpl;
import com.ssafy.backend.user.service.LoginService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	@Autowired
	private LoginService service;
	
	@ApiOperation(value = "로그인하기", notes = "사용자의 정보를 입력받아 로그인을 검증합니다..")
	@ApiResponses({
		@ApiResponse(code = 200, message = "로그인 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/confirm")
	public ResponseEntity<Map<String, Object>> login(@RequestBody UserModel model) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			UserModel loginUser = service.login(model);
			
			if(loginUser != null) {	
				String token = jwtService.create("userid", loginUser.getUserID(), "access-token");
				resultMap.put("access-token", token);
				resultMap.put("message", token);
				
			} else {	
				resultMap.put("message", "fail");
			}
			status = HttpStatus.ACCEPTED;
			
		} catch(Exception e) {	
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	@ApiOperation(value = "사용자 검증", notes = "해당 사용자가 맞는지 검증합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "사용자 검증 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@GetMapping("/info/{userid}")
    public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userID") String userID, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            try {
                UserModel model = service.userInfo(userID);
                
                resultMap.put("userInfo", model);
                resultMap.put("message", "success");
                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            resultMap.put("message", "fail");
            status = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<>(resultMap, status);
    }
}
