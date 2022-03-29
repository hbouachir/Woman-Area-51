package tn.esprit.spring.womanarea51.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.Pub;



public interface IPubService {
	public Pub add(Pub p,Long idUser);
	public Pub addPubImage( MultipartFile file, Long idPub);
	

}
