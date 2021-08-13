package com.ssafy.backend;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.ssafy.backend.socket.SocketHandler;
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new SocketHandler(), "/replyEcho").setAllowedOrigins("*");
	}
}