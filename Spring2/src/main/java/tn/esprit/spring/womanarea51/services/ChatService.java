package tn.esprit.spring.womanarea51.services;

import org.springframework.stereotype.Service;

import tn.esprit.spring.womanarea51.bot;

@Service
public class ChatService implements IChatService{
	
	@Override
	public String sendMessage(String m) {
		// TODO Auto-generated method stub
		return bot.chat(m);
		 
	}

}
