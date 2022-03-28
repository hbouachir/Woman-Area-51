package tn.esprit.spring.womanarea51.services;

import java.util.List;

import tn.esprit.spring.womanarea51.entities.Mensaje;





public interface IMessageService {
public Mensaje createMessage(Mensaje message); 
public List<Mensaje> getAllMessages(String name1,String name2);
public List<Mensaje> getAllMessage();
}
