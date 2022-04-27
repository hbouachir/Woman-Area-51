package tn.esprit.spring.womanarea51.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;


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

	//@Scheduled(fixedRate = 20000)
	//@Override
	public String listPubFb(){
		String token="EAAUZArboqDKYBAHGICzEQjesYt3XolLyt4NRkLiKF76GLp9ztRXYyx3tJlxxcfXeZBK36Crw5RQascZAQjzxDHcNjL8UTEZBkHlYsy9vEKmjQRQD0KKuqXQYQRGDqWHdx24rvwRB7fhvZAZAsXkdsVeTSbqqZAHsz4qqqNTZAb04YAZAHZCxakUGJ2RK7gQ3rizYT6cXvsnsQMXgZDZD";

		String msg="";
		//InputStream	inputfile = file.getInputStream();
		FacebookClient fb= new DefaultFacebookClient(token);
		//   User u= fb.fetchObject("me/account",User.class);
		FacebookType response=fb.publish("me/feed", FacebookType.class,  Parameter.with("message", "Pub woman area 51 site"),Parameter.with("link", "www.google.com"));	

		   msg="doneeee";
return msg;
	}
	// @Scheduled(fixedRate = 3000000)
	//@Scheduled(fixedRate = 30000)
	 public void AddPubSch(){
		 Integer i = 2;
		 Integer u=12;
		 
		 Long l = new Long(i);
		 Long lu = new Long(u);
			String token="EAAUZArboqDKYBAHGICzEQjesYt3XolLyt4NRkLiKF76GLp9ztRXYyx3tJlxxcfXeZBK36Crw5RQascZAQjzxDHcNjL8UTEZBkHlYsy9vEKmjQRQD0KKuqXQYQRGDqWHdx24rvwRB7fhvZAZAsXkdsVeTSbqqZAHsz4qqqNTZAb04YAZAHZCxakUGJ2RK7gQ3rizYT6cXvsnsQMXgZDZD";

			String msg="";
			Pub pub = pubRepository.findById(l).orElse(null);
		//	Pub pubu = pubRepository.findById(lu).orElse(null);
			 
			try {
				//FileInputStream file = new FileInputStream(new File("D:\\arton5595.JPG"));
				FileInputStream file = new FileInputStream(new File("D:\\woman1.jpg"));
			//	FileInputStream filee = new FileInputStream(new File(pubu.getImage()));
				FacebookClient fb= new DefaultFacebookClient(token);
				FacebookType response=fb.publish("me/photos", FacebookType.class,BinaryAttachment.with("woman1.jpg", file),Parameter.with("message", pub.getTitle()));	
			//	FacebookType response2=fb.publish("me/photos", FacebookType.class,BinaryAttachment.with("woman_area_logo.jpg", filee),Parameter.with("message", pubu.getTitle()));	

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
	 }
}
