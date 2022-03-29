package tn.esprit.spring.services;

import org.springframework.stereotype.Service;

import tn.esprit.spring.bot;
import tn.esprit.spring.entities.Message;

@Service
public class ChatService implements IChatService{
	
	@Override
	public String sendMessage(String m) {
		// TODO Auto-generated method stub
		return bot.chat(m);
		 
	}

}
