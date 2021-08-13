package com.ssafy.backend.coupon.mapper;

import org.springframework.stereotype.Repository;

import com.ssafy.backend.coupon.model.CouponModel;
import com.ssafy.backend.coupon.model.GameIDModel;

@Repository
public interface CouponMapper {
	int insertAFKCoupon(CouponModel model) throws Exception;
	
	int updateAFKArenaUID(GameIDModel model) throws Exception;
	
	String selectAFKArenaUID(String userID) throws Exception;
	
	GameIDModel getGameUID(String userID) throws Exception;
}
