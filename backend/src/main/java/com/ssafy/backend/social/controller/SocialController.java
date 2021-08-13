package com.ssafy.backend.social.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.backend.social.model.SocialModel;
import com.ssafy.backend.social.service.SocialService;
import com.ssafy.backend.user.model.UserModel;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins= {"*"},maxAge=6000)
@RequestMapping("/social")
public class SocialController {
	
	@Autowired
	private SocialService service;
	
	@ApiOperation(value = "팔로우 명단 가져오기", notes = "해당 유저의 팔로우 명단을 불러옵니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "팔로우 목록 불러오기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/follower")
	public ResponseEntity<?> findFollower(@RequestBody UserModel model){
		List<String> list=service.findFollower(model.getUserID());
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@ApiOperation(value = "팔로잉 명단 가져오기", notes = "해당 유저의 팔로잉 명단을 불러옵니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "팔로잉 목록 불러오기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/following")
	public ResponseEntity<?> findFollowing(@RequestBody UserModel model){
		List<String> list=service.findFollowing(model.getUserID());
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@ApiOperation(value = "팔로우 요청 명단 가져오기", notes = "나에게 팔로우를 건 사람들의 목록을 가져옵니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "팔로우 목록 불러오기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/findrequestFollow")
	public ResponseEntity<?> findreqeustFollow(@RequestBody SocialModel model){
		List<String> list=service.findrequestFollow(model.getTargetID());
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@ApiOperation(value = "팔로우하기", notes = "다른 사람을 팔로우합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "팔로우 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/requestFollow")
	public ResponseEntity<String> requestFollow(@RequestBody SocialModel model,HttpServletRequest request){
		HttpSession session=request.getSession();
		System.out.println(session.getAttribute("userID"));
		SocialModel temp=service.checkDuplicate(model);//중복성검사하는 단계
		String msg="";
		if(temp==null) {
			int result=service.requestFollow(model);
			msg="success";
		}else msg="fail";
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@ApiOperation(value = "팔로우 요청 수락하기", notes = "다른 사람이 요청한 팔로우를 수락합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "팔로우 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/allowFollow")
	public ResponseEntity<String> allowFollow(@RequestBody SocialModel model){
		String msg="";
		System.out.println(model.getSourceID());
		int result=service.allowFollow(model);
		if(result>=1)msg="success";
		else msg="fail";
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@ApiOperation(value = "팔로우 끊기", notes = "다른 사람과의 팔로우를 끊습니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "팔로우 끊기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/cancleFollow")
	public ResponseEntity<String> cancleFollow(@RequestBody SocialModel model){
		String msg="";
		System.out.println(model.getTargetID());
		int result=service.cancleFollow(model);
		if(result>=1)msg="success";
		else msg="fail";
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@ApiOperation(value = "팔로우 요청중인지 확인하기", notes = "자신이 팔로우를 요청중인지 확인합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "확인 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/findrequestFollowOne")
	public ResponseEntity<Object> findreqeustFollowOne(@RequestBody SocialModel model){
		SocialModel temp=service.findrequestFollowOne(model);
		return new ResponseEntity<>(temp,HttpStatus.OK);
	}
	
	@ApiOperation(value = "팔로우 요청 취소", notes = "요청했던 팔로우를 취소합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "취소 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/canclerequestFollow")
	public ResponseEntity<String> canclerequestFollow(@RequestBody SocialModel model){
		int result=service.canclerequestFollow(model);
		String msg="";
		if(result>0)msg="success";
		else msg="fail";
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@ApiOperation(value = "알람 찾기", notes = "팔로우 알람을 찾습니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "알람 찾기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/findAlaram")
	public ResponseEntity<Object> findAlaram(@RequestBody SocialModel model){
		List<SocialModel> result=service.findAlarm(model.getTargetID());
		return new ResponseEntity<Object>(result,HttpStatus.OK);
	}
	
	@ApiOperation(value = "알람 읽기", notes = "팔로우 알람을 읽었습니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "알람 읽기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/readAlarm")
	public ResponseEntity<String> readAlarm(
			@RequestParam("sourceID")String sourceID,
			@RequestParam("targetID")String targetID
	){
		SocialModel model=new SocialModel();
		model.setSourceID(sourceID);
		model.setTargetID(targetID);
		String msg="";
		int result=service.readAlarm(model);
		if(result>=1) msg="success";
		else msg="fail";
		
				return new ResponseEntity<String>(msg,HttpStatus.OK);			
	}
	
	
}
