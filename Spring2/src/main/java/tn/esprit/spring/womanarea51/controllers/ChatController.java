package tn.esprit.spring.womanarea51.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.womanarea51.entities.Mensaje;
import tn.esprit.spring.womanarea51.repositories.MessageRepository;
import tn.esprit.spring.womanarea51.services.IMessageService;





@Controller
public class ChatController {
 //String Mensaje;
 @Autowired
 IMessageService imsg;
 @Autowired
 MessageRepository mr;
	private String[]colors ={"red","green","orange","blue","purple"};
	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")
	public Mensaje recibirMensaje(Mensaje mensaje){
		mensaje.setFecha(new Date().getTime());
		if(mensaje.getTipo().equals("new_user")){
			mensaje.setColor(colors[new Random().nextInt(colors.length)]);
			mensaje.setTexto(" - new user connecter");
		}else {
			imsg.createMessage(mensaje);
		}
	//	mensaje.setTexto("recibido por el broker :"+mensaje.getTexto());
		
	
		return mensaje;
		
		
	}
	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	public String comprobarSiElUsuarioEstaEscribiendo(String username){
	return username.concat(" est entrain d'ecrire.......");
	}
}
