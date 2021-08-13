package com.ssafy.backend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssafy.backend.coupon.service.CouponService;
import com.ssafy.backend.coupon.service.CouponServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponScraperTest {
	
	@Autowired
	CouponService service = new CouponServiceImpl();
	
	@Test
	public void scraperTest() throws Exception {
		System.out.println("test~!");
		service.getAFKArenaCoupon();
	}
}
