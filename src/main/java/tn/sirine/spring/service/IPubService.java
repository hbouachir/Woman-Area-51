package tn.sirine.spring.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.restfb.Connection;
import com.restfb.types.User;

import tn.sirine.spring.entities.Pub;



public interface IPubService {
	public Pub add(Pub p,Long idUser);
	public Pub addPubImage( MultipartFile file, Long idPub);
	public void AddPub(MultipartFile file, Long idPub) ;
	public void AddPubLink(Long idPub) ;
	public String listPubFb();
}
