package tn.esprit.spring.womanarea51.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.womanarea51.entities.Mensaje;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.MessageRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;





@Service
public class MessageService implements IMessageService{
@Autowired
MessageRepository msg;
@Autowired
UserRepository ur;

	@Override
	public Mensaje createMessage(Mensaje message) {
		// TODO Auto-generated method stub
		Optional<User> u= ur.findByUsername(message.getUsername());
		Optional<User> u1= ur.findByUsername(message.getUser());
		message.setUserfrom(u.get());
		message.setUserto(u1.get());
		return msg.save(message);
	}

	@Override
	public List<Mensaje> getAllMessages(String name1,String name2) {
		/*User f = userRepository.findById(idUserf).orElse(null);

		User t = userRepository.findById(idUsert).orElse(null);*/
		List<Mensaje> m = msg.findAllMensajesBetweenTwoUsers(name1, name2);
		//List<Mensaje> m= (List<Mensaje>) msg.findAll();
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

	@Override
	public void delete(String name1, String name2) {
		// TODO Auto-generated method stub
		List<Mensaje> m = msg.findAllMensajesBetweenTwoUsers(name1, name2);
		m.forEach(entity -> msg.delete(entity));
	}

	@Override
	public void deleteById(Long idMsg) {
		msg.deleteById(idMsg);
		
	}
}
