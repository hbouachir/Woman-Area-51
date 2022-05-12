package tn.esprit.spring.womanarea51.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfigchat implements WebSocketMessageBrokerConfigurer {
  
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
	 /*registry.addEndpoint("/gs-guide-websocket")
	 .withSockJS();*/
	 registry.addEndpoint("/chat-websocket")
	 .setAllowedOrigins("https://womanarea51.ml", "https://www.womanarea51.ml")
	 .withSockJS();
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
	   registry.enableSimpleBroker("/chat/");
	   registry.setApplicationDestinationPrefixes("/app/");
	}
	
}
