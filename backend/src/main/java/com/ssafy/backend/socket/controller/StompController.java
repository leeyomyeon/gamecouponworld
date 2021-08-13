package com.ssafy.backend.socket.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;import com.kenai.constantine.platform.Sock;
import com.ssafy.backend.social.mapper.SocialMapper;
import com.ssafy.backend.social.model.SocialModel;
import com.ssafy.backend.socket.SocketHandler;
import com.ssafy.backend.socket.model.SocketModel;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StompController {
	private final SimpMessagingTemplate template;
	
	@Autowired
	private SocialMapper beanMapper;
	public static SocialMapper mapper;
	
	@PostConstruct
	private void initialize() {
		StompController.mapper=beanMapper;
	}
	
	
//	@MessageMapping("/TTT")
//	@SendTo("/topic/message")
//	public SocketModel ttt(SocketModel model) throws Exception{
//		System.out.println(model.getUserName());
//		String userName=model.getUserName();
//		String content=model.getContent();
//		SocketModel result=new SocketModel(userName,content);
//		return result;
//	}
	@MessageMapping("/TTT")
	public void ttt(SocketModel model) {
		System.out.println(model.getTargetID());
		template.convertAndSend("/topic/"+model.getTargetID(),model.getSourceID()+"님이 "+model.getTargetID()+"님을 팔로우 신청했습니다.!");
	}
	
	@MessageMapping("/AlarmCnt")
	public void AlarmCnt(SocketModel model) {
		List<SocialModel> result= mapper.findAlaram(model.getTargetID());
		System.out.println(result.size());
		template.convertAndSend("/topic/alarm/"+model.getTargetID(),result);
	}
}
