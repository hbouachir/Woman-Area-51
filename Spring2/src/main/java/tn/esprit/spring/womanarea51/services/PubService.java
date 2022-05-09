package tn.esprit.spring.womanarea51.services;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

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

import tn.esprit.spring.womanarea51.entities.Filepost;
import tn.esprit.spring.womanarea51.entities.Filepub;
import tn.esprit.spring.womanarea51.entities.Post;
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
	@Override
	public List<Pub> getAllPub() {
		// TODO Auto-generated method stub
		return pubRepository.findAllByOrderByIdDesc();
	}
	@Override
	public Pub getPub(Long id) {
		// TODO Auto-generated method stub
		return pubRepository.findById(id).get();
	}
	@Override
	public void deletePub(Long id) {
		// TODO Auto-generated method stub
		pubRepository.deleteById(id);
	}
	@Override
	public Pub upPub(Pub p) {
		// TODO Auto-generated method stub
		return pubRepository.save(p);
	}
	@Override
	public void partageFb(String token, Long id) {
		// TODO Auto-generated method stub
		Pub p=	pubRepository.findById(id).get();
		String url="";
		String namefile="";
		Set<Filepub> f= p.getFilespub();
	System.out.println("ffffffffffffffffffff");
	try {
	
		for (Filepub o : f) {
			 url=o.getFilePath();
			namefile=o.getFileName();
			}
		
		System.out.println("ttttttttt"+url);
		try {BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
				  FileOutputStream fileOutputStream = new FileOutputStream(namefile) ;
				    byte dataBuffer[] = new byte[1024];
				    int bytesRead;
				    System.out.println("before while");
				    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				        fileOutputStream.write(dataBuffer, 0, bytesRead);
				    }
				    System.out.println("before hello");
				   // FileInputStream file = new FileInputStream();
				    System.out.println("hello");
					FacebookClient fb= new DefaultFacebookClient(token);
					FacebookType response=fb.publish("me/photos", FacebookType.class,BinaryAttachment.with(namefile, in),Parameter.with("message", p.getTitle()));
					System.out.println("bye");
				} catch (IOException e) {
				    // handle exception
				}
		
		System.out.println("oooooooooooooooo"+namefile);

	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	@Override
	public void AddPub(Long id , MultipartFile file,String token) {
		//Publicite PubWithImg = AffecterImageVideoPubs(pub, file);
		//String typefile = TypeFile(file);
		Pub p=	pubRepository.findById(id).get();
		
		
		
				InputStream inputfile;
				try {
					inputfile = file.getInputStream();
					FacebookClient fb= new DefaultFacebookClient(token);
					FacebookType response=fb.publish("me/photos", FacebookType.class,BinaryAttachment.with(file.getName(), inputfile), Parameter.with("message", p.getTitle()));
					System.out.println(response.getId());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		
		
		//return PubWithImg ;

}
}
