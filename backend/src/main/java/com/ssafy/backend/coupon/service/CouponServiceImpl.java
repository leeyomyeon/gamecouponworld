package com.ssafy.backend.coupon.service;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.backend.coupon.mapper.CouponMapper;
import com.ssafy.backend.coupon.model.GameIDModel;

@Service
public class CouponServiceImpl implements CouponService {
	
	final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36";
	
	@Autowired
	private CouponMapper mapper;
	
	@Override
	public List<String> getAFKArenaCoupon() throws Exception {
		final String URL = "https://www.afkarena.net/redemption-codes";
		
		Document doc = Jsoup.connect(URL).get();
		
		Elements couponList = doc.select("input");
		
		List<String> list = new ArrayList<>();
		
		for(Element c : couponList) {
			String coupon = c.val();
			System.out.println(coupon);
			if(coupon.equals("")) {
				break;
			} else {
				list.add(coupon);
			}
		}
		
		return list;
	}

	@Override
	public int updateAFKArenaUID(GameIDModel model) throws Exception {
		return mapper.updateAFKArenaUID(model);
	}
	
	@Override
	public GameIDModel getGameUID(String userID) throws Exception {
		return mapper.getGameUID(userID);
	}
	
	// 132597005
	@Override
	public void registAFKArenaCoupon(String userID, String verifyCode) throws Exception {
		
		final String URL = "https://cdkey.lilith.com/api/verify-afk-code";
		
		String AFKArenaUID = mapper.selectAFKArenaUID(userID);
		
		String jsonBody = "{\"uid\":" + AFKArenaUID + ", \"game\":\"afk\", \"code\" : \"" + verifyCode + "\"}";
		setSSL();
		
		Connection.Response getLoginCookie = Jsoup.connect(URL)
				.userAgent(userAgent)
				.header("Content-Type", "application/json;charset=UTF-8")
				.header("Accept", "application/json")
                .followRedirects(true)
                .ignoreHttpErrors(true)
				.ignoreContentType(true)
				.header("referer", "https://cdkey.lilith.com/afk-global")
				.requestBody(jsonBody)
				.method(Connection.Method.POST)
				.execute();
		
		System.out.println(getLoginCookie.statusCode());
		
		String registURL = "https://cdkey.lilith.com/api/cd-key/consume";
		List<String> couponList = getAFKArenaCoupon();
		
		for(String coupon : couponList) {
			String jsonBody1 = "{\"type\":\"cdkey_web\", \"game\":\"afk\", \"uid\" : " + AFKArenaUID + ", \"cdkey\" : \""+ coupon +"\"}";
			Connection.Response registCoupon = Jsoup.connect(registURL)
					.userAgent(userAgent)
					.header("Content-Type", "application/json;charset=UTF-8")
					.header("Accept", "application/json")
					.cookies(getLoginCookie.cookies())
//					.data("id", AFKArenaUID)
	                .followRedirects(true)
	                .ignoreHttpErrors(true)
					.ignoreContentType(true)
					.header("referer", "https://cdkey.lilith.com/afk-global")
					.requestBody(jsonBody1)
					.method(Connection.Method.POST)
					.execute();
			System.out.println(registCoupon.statusCode());
//			int res = registCoupon.statusCode();
//			if(res == 200) {
//				System.out.println("쿠폰 입력 성공");
//			} else {
//				System.out.println("사용 기간이 만료되었거나 이미 사용이 완료된 쿠폰입니다.");
//			}
		}
	}
	
	public static void setSSL() throws NoSuchAlgorithmException, KeyManagementException {
		TrustManager[] trustAllCerts = new TrustManager[] {
			new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() { return null; }

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
			}
		};
		
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new SecureRandom());
		
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) { return true; }
		}); 
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory()); 
	}
}
