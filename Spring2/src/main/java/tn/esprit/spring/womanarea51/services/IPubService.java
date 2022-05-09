package tn.esprit.spring.womanarea51.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.Post;
import tn.esprit.spring.womanarea51.entities.Pub;



public interface IPubService {
	public Pub add(Pub p,Long idUser);
	public Pub addPubImage( MultipartFile file, Long idPub);
	public List<Pub> getAllPub();
	public Pub getPub(Long id);
	public void deletePub(Long id) ;
	public Pub upPub(Pub p);
	public void partageFb(String token,Long id);
	public void AddPub(Long id , MultipartFile file,String token);

}
