package com.ssafy.backend.coupon.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.ssafy.backend.coupon.model.GameIDModel;

public interface CouponService {
	List<String> getAFKArenaCoupon() throws Exception;
	
	int updateAFKArenaUID(GameIDModel model) throws Exception;
	
	void registAFKArenaCoupon(@Param("userID") String userID, @Param("verifyCode") String verifyCode) throws Exception;
	
	GameIDModel getGameUID(String userID) throws Exception;
}
