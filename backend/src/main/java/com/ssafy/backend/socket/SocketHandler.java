package com.ssafy.backend.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.ssafy.backend.social.mapper.SocialMapper;


@Component
public class SocketHandler extends TextWebSocketHandler {
	List<WebSocketSession> sessions=new ArrayList<WebSocketSession>();
	HashMap<String, WebSocketSession> userSessions = new HashMap<>(); //웹소켓 세션을 담아둘 맵
	String senderID;
	
	@Autowired
	private SocialMapper beanMapper;
	public static SocialMapper mapper;
	
	@PostConstruct
	private void initialize() {
		SocketHandler.mapper = beanMapper;
	}
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		System.out.println("handleTextMessage: "+session+" :"+message);
		List<String> list=mapper.findFollowing("gkgk246");
		System.out.println(list.size());
		//특정 유저에게 보내기
		String msg=message.getPayload();
		if(msg!=null) {
			String[] str=msg.split(",");
			if(str.length==1) {
				senderID=str[0];
				userSessions.put(senderID, session);
				sessions.add(session);				
				System.out.println(senderID+" 연결 성공");
			}
			else {
				String caller=str[0];
				String target=str[1];
				WebSocketSession targetSession=userSessions.get(target);
				if(targetSession!=null) {
					TextMessage tmpMsg=new TextMessage(caller+"님이 "+ target+"님을 팔로우요청을 하였습니다.!");
					targetSession.sendMessage(tmpMsg);
				}				
			}
		}
		
//		for( WebSocketSession sess: sessions) {
//			sess.sendMessage(new TextMessage(senderId+" : "+message.getPayload()));
//		}
		//메시지 발송
//		String msg = message.getPayload();
//		for(String key : sessionMap.keySet()) {
//			WebSocketSession wss = sessionMap.get(key);
//			try {
//				wss.sendMessage(new TextMessage(msg));
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//소켓 연결
//		super.afterConnectionEstablished(session);
//		sessionMap.put(session.getId(), session);
		System.out.println("AfterConnectionEstablished: "+ session);
//		if(senderID!=null) {	// 로그인 값이 있는 경우만
//			System.out.println(senderID + " 연결 됨");
//			userSessions.put(senderID, session);
//			sessions.add(session);
//		}
//		String senderId = getId(session);
//		userSessions.put(senderId, session);
	}
	
//	private String getId(WebSocketSession session) {
//		Map<String, Object> httpSession = session.getAttributes();
//		System.out.println(httpSession);
//		String loginuser= (String)httpSession.get("userID");
//		System.out.println(loginuser);
//		return loginuser==null? null: loginuser;
//	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//소켓 종료
		if(senderID!=null) {	// 로그인 값이 있는 경우만
			System.out.println(senderID + " 연결 종료됨");
			userSessions.remove(senderID);
			sessions.remove(session);
		}
	}
}
