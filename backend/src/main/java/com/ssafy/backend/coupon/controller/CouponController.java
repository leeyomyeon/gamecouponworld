package com.ssafy.backend.coupon.controller;

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

import com.ssafy.backend.coupon.model.GameIDModel;
import com.ssafy.backend.coupon.service.CouponService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/coupon")
@Api("Coupon Controller API")
public class CouponController {
	@Autowired
	private CouponService service;
	
	@ApiOperation(value = "AFKArena UID 등록", notes = "AFKArena UID 등록")
	@ApiResponses({
		@ApiResponse(code = 200, message = "UID 등록 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/update/AFKArenaUID")
	public ResponseEntity<?> updateAFKArenaUID(@RequestBody GameIDModel model) throws Exception {
		service.updateAFKArenaUID(model);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "AFKArena 쿠폰 자동 등록", notes = "AFKArena 쿠폰 자동 등록")
	@ApiResponses({
		@ApiResponse(code = 200, message = "쿠폰 등록 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@GetMapping("/regist/AFKArena/{userID}/{verifyCode}")
	public ResponseEntity<?> registAFKArenaCoupon(@PathVariable String userID,
												  @PathVariable String verifyCode) throws Exception {
		service.registAFKArenaCoupon(userID, verifyCode);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자의 Game UID 가져오기", notes = "사용자의 Game UID 가져오기")
	@ApiResponses({
		@ApiResponse(code = 200, message = "조회 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@GetMapping("/getGameUID/{userID}")
	public ResponseEntity<?> getGameUID(@PathVariable String userID) throws Exception {
		GameIDModel model = service.getGameUID(userID);
		
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
}
