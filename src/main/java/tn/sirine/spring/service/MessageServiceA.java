package tn.sirine.spring.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.sirine.spring.entities.Mensaje;
import tn.sirine.spring.entities.User;
import tn.sirine.spring.repository.MessageRepository;
import tn.sirine.spring.repository.UserRepository;


@Service
public class MessageServiceA implements IMessageServiceA{
@Autowired
MessageRepository msg;
@Autowired
UserRepository ur;

	@Override
	public Mensaje createMessage(Mensaje message) {
		// TODO Auto-generated method stub
		User u= ur.findByName(message.getUsername());
		User u1= ur.findByName(message.getUser());
		message.setUserfrom(u);
		message.setUserto(u1);
		return msg.save(message);
	}

	@Override
	public List<Mensaje> getAllMessages(String name1,String name2) {
		/*User f = userRepository.findById(idUserf).orElse(null);

		User t = userRepository.findById(idUsert).orElse(null);*/
		//List<Mensaje> m = msg.findAllMensajesBetweenTwoUsers(name1, name2);
		List<Mensaje> m= (List<Mensaje>) msg.findAll();
		return m;
	}
	@Override
	public List<Mensaje> getAllMessage() {
		/*User f = userRepository.findById(idUserf).orElse(null);

		User t = userRepository.findById(idUsert).orElse(null);*/
		//List<Mensaje> m = msg.findAllMensajesBetweenTwoUsers(name1, name2);
		List<Mensaje> m= (List<Mensaje>) msg.findAll();
		return m;
	}
}
