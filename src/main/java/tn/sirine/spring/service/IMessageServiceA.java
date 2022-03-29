package tn.sirine.spring.service;

import java.util.List;

import tn.sirine.spring.entities.Mensaje;


public interface IMessageServiceA {
public Mensaje createMessage(Mensaje message); 
public List<Mensaje> getAllMessages(String name1,String name2);
public List<Mensaje> getAllMessage();
}
