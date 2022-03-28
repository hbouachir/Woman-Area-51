package tn.esprit.spring.womanarea51.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.spring.womanarea51.entities.Pub;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.PubRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;

@Service
public class PubService implements IPubService{

	@Autowired
	PubRepository pubRepository;
	@Autowired
	UserRepository userRepository;
	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

	
	@Override
	public Pub add(Pub p,Long idUser) {
		// TODO Auto-generated method stub
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		User u= userRepository.findById(idUser).orElse(null);
		p.setUserpub(u);
p.setCreatedate(currentTimestamp);
		return pubRepository.save(p);
	}
	@Override
	public Pub addPubImage(MultipartFile file, Long idPub) {
		Pub p = pubRepository.findById(idPub).orElse(null);
		String url=uploadFile(file) ;
		p.setImage(url);
		return pubRepository.save(p);
	}
	private String uploadFile(MultipartFile uploadFile) {
	    String url = "";
	    try {
	     
	     url= Paths.get(uploadDirectory, uploadFile.getOriginalFilename()).toString();
	
	    } catch (Exception e) {
	      url = "Could not upload the file: " + uploadFile.getOriginalFilename() + "!";
	     
	    }
	    return url;
	  }
	
}
