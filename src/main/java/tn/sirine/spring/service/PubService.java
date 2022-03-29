package tn.sirine.spring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.BinaryAttachment;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

import tn.sirine.spring.entities.Pub;
import tn.sirine.spring.entities.User;
import tn.sirine.spring.repository.PubRepository;
import tn.sirine.spring.repository.UserRepository;

@EnableScheduling
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
pubRepository.save(p);
		return p;
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
	@Override
	public void AddPub(MultipartFile file, Long idPub)   {
		Pub p = pubRepository.findById(idPub).orElse(null);
		Pub pub= addPubImage(file,idPub);
		
	String fileName= p.getImage();
		
		try {
			String token="EAAUZArboqDKYBAIZCCjoWH6Dpb3b2MZA0H7Tbj8IRxHp8ku0MThHZAZCPVsKuqbAYNYaIUvIvnfG9KZBb0nmdBXfq7vehUWjZCNsBZB7gUzRMKH1iXpcGWGMEtzy4GbLST7ZA6Pw5nnGXQCYjJcQZDZD";
			InputStream	inputfile = file.getInputStream();
			
			FacebookClient fb= new DefaultFacebookClient(token);
			FacebookType response=fb.publish("me/photos", FacebookType.class,BinaryAttachment.with(file.getName(), inputfile),Parameter.with("message", pub.getTitle()));	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Override
	public void AddPubLink(Long idPub)   {
		Pub pub = pubRepository.findById(idPub).orElse(null);
	
			String token="DfZBmEs3rnv4HI7tM9ECDA0VhAxPlQazHslOp0mnMZAKTqYufR7AG02pjqSAZDZD";

			
			FacebookClient fb= new DefaultFacebookClient(token);
			
			FacebookType response=fb.publish("me/feed", FacebookType.class,  Parameter.with("message", pub.getTitle()),Parameter.with("link", "www.google.com"));	
			
	}
	@Scheduled(fixedRate = 20000)
	@Override
	public String listPubFb(){
		String token="EKb1kwwAZDZD";
String msg="";
		//InputStream	inputfile = file.getInputStream();
		FacebookClient fb= new DefaultFacebookClient(token);
		//   User u= fb.fetchObject("me/account",User.class);
		FacebookType response=fb.publish("me/feed", FacebookType.class,  Parameter.with("message", "Pub woman area 51 site"),Parameter.with("link", "www.google.com"));	

		   msg="doneeee";
return msg;
	}
	// @Scheduled(fixedRate = 3000000)
	@Scheduled(fixedRate = 20000)
	 public void AddPubSch(){
		 Integer i = 13;
		 Integer u=12;
		 
		 Long l = new Long(i);
		 Long lu = new Long(u);
			String token="x1mKupyP24nKb1kwwd3HpUNSezqpzhDgrisMUg6ssQ3YGZCw3vkPWB24sEZBPVAbKmfdwjMRZCftTwzHFgEXO6v3Uhm3StZAW1OlAZDZD";

			String msg="";
			Pub pub = pubRepository.findById(l).orElse(null);
			Pub pubu = pubRepository.findById(lu).orElse(null);
			 
			try {
				FileInputStream file = new FileInputStream(new File(pub.getImage()));
				FileInputStream filee = new FileInputStream(new File(pubu.getImage()));
				FacebookClient fb= new DefaultFacebookClient(token);
				FacebookType response=fb.publish("me/photos", FacebookType.class,BinaryAttachment.with("woman1.jpg", file),Parameter.with("message", pub.getTitle()));	
		
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
	 }
}
