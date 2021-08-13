package com.ssafy.backend.user.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.backend.amazonS3.S3Uploader;
import com.ssafy.backend.user.model.UserModel;
import com.ssafy.backend.user.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins= {"*"},maxAge=6000)
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	S3Uploader s3UPloader;
	
	@ApiOperation(value = "회원가입 하기", notes = "회원가입")
	@ApiResponses({
		@ApiResponse(code = 200, message = "회원가입 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody UserModel model){
		String msg="";
		System.out.println(model.getUserID());
		HttpStatus status;
		try {
			int result=service.userRegister(model);
			System.out.println(result);
			if(result>=1) {
				msg="success";
				service.insertGameID(model.getUserID());
			}else {
				msg="fail";
			}
			status=HttpStatus.ACCEPTED;
		}catch(Exception e) {
			System.out.println(e);
			msg="error";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(msg,status);
	}
	
	@ApiOperation(value = "회원 정보 보기", notes = "특정 회원의 정보를 봅니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "회원 정보 조회 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/info")
	public ResponseEntity<Map<String, Object>> userInfo(@RequestBody UserModel model){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		String userId=model.getUserID();
		try {
			UserModel user=service.getUserInfo(userId);
			resultMap.put("userInfo", user);
			resultMap.put("meesage", "success");
			status=HttpStatus.ACCEPTED;			
		}catch(Exception e){
			resultMap.put("message", e.getMessage());
			status=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap,status);
	}
	
	
	//회원정보 수정
	@ApiOperation(value = "회원 정보 수정", notes = "특정 사용자의 정보를 수정합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "로그인 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/modify")
	public ResponseEntity<String> userModify(
			@RequestParam(value="imageData",required=false) MultipartFile multipartFile,
			@RequestParam("userID") String userID,
			@RequestParam("userName") String userName,
			@RequestParam("password") String password,
			@RequestParam("email") String email,
			@RequestParam("alias") String alias,
			@RequestParam(value="bio",required=false) String bio,
			@RequestParam(value="profilePath",required=false)String profilePath
			) throws IllegalArgumentException, FileNotFoundException, IOException{
		System.out.println(multipartFile);
		String msg="";
		HttpStatus status;
		UserModel model=new UserModel();
		model.setUserID(userID);
		model.setUserName(userName);
		model.setPassword(password);
		model.setEmail(email);
		model.setAlias(alias);
		model.setBio(bio);
		if(multipartFile!=null) {
			model.setProfilePath(s3UPloader.upload(multipartFile, "static"));			
		}else if(multipartFile==null&&profilePath!=null) {
			model.setProfilePath(profilePath);
		}
		try {
			int result=service.userModify(model);
			if(result>=1)msg="success";
			else msg="fail";
			status=HttpStatus.ACCEPTED;
		}catch(Exception e){
			msg="error";
			status=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(msg,status);
	}
	
	@ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "회원 탈퇴 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/delete")
	public ResponseEntity<String> userDelte(@RequestBody UserModel model){
		String msg="";
		HttpStatus status;
		String userID=model.getUserID();
		try {
			int result=service.userDelete(userID);
			if(result>=1)msg="success";
			else msg="fail";
			status=HttpStatus.ACCEPTED;
		}catch(Exception e){
			msg="error";
			status=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(msg,status);
	}
	
	@ApiOperation(value = "회원 검색해서 목록 가져오기", notes = "특정 문자열이 들어간 유저들을 검색합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "로그인 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@GetMapping("/list/{userID}")
	public ResponseEntity<?> searchUserList(@PathVariable("userID") String userID) throws Exception {
		List<String> list = service.searchUserList(userID);
		
		if (list.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	//아래부터는 메일 서비스를 위한 확인 절차
	@ApiOperation(value = "이메일 인증하기", notes = "회원가입 시 이메일 인증을 합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "로그인 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@GetMapping("/mail/{email}")
	public ResponseEntity<Integer> email(@PathVariable("email") String email){
		System.out.println(email);
		UserModel user=service.getUserInfoByEmail(email);
		if(user!=null) {
			return new ResponseEntity<Integer>(1,HttpStatus.OK);
		}
		Random r = new Random();
		int dice=r.nextInt(458932)+49311;//난수생성
		String setfrom= "gkgkgk4610@gamil.com";
		String tomail= email;
		String title="회원가입 인증 이메일 입니다.";//제목
		String content =
	            
	            System.getProperty("line.separator")+ //한줄씩 줄간격을 두기위해 작성
	            
	            System.getProperty("line.separator")+
	                    
	            "안녕하세요 회원님 저희 홈페이지를 찾아주셔서 감사합니다"
	            
	            +System.getProperty("line.separator")+
	            
	            System.getProperty("line.separator")+
	    
	            " 인증번호는 " +dice+ " 입니다. "
	            
	            +System.getProperty("line.separator")+
	            
	            System.getProperty("line.separator")+
	            
	            "받으신 인증번호를 홈페이지에 입력해 주시면 다음으로 넘어갑니다.";
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,
                    true, "UTF-8");
			messageHelper.setFrom(setfrom);
			messageHelper.setTo(tomail);
			messageHelper.setSubject(title);
			messageHelper.setText(content);
			
			mailSender.send(message);
			System.out.println("성공?");
		}catch(Exception e) {
			System.out.println(e);
		}
		return new ResponseEntity<Integer>(dice,HttpStatus.OK); 
	}
}
