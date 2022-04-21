package tn.esprit.spring.womanarea51.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.Post;
import tn.esprit.spring.womanarea51.entities.RatePub;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.PostRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.security.services.UserDetailsImpl;
import tn.esprit.spring.womanarea51.services.PostServiceimpl;
import tn.esprit.spring.womanarea51.services.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RestController
public class PostController {
	@Autowired
	UserService userService;
	
	@Autowired
	PostServiceimpl postService;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	PostRepository pr;
	
	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";
////ok
	@PostMapping("addpost")
	public String createNewPost( @RequestBody Post post, Authentication authentication) { 
	String msg="";
	UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
    User U = userRepository.findByUsername(U1.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
    postService.addPost(post, U.getId());
	
msg="post ajouté de user name: "+U.getFirstName()+"  "+U.getLastName();
	return msg; 
	}
	///ok
	 @PostMapping("/upload/{idPost}")
	  public Post uploadFile(@RequestParam("file") MultipartFile uploadFile,@PathVariable("idPost")Long idPost) {
	    String message = "";
	    try {
	    Post p=	postService.addPostImageTest(uploadFile,idPost);
	      message= uploadFile.getOriginalFilename();
	      
	    
	    } catch (Exception e) {
	      message = "Could not upload the file: " + uploadFile.getOriginalFilename() + "!";
	    }
	    return postService.addPostImageTest(uploadFile,idPost);
	  }
	 ///ookk
	 @RequestMapping(value = "/postup", method = RequestMethod.PUT)
		
	 public void updatePost(@RequestBody Post p,Authentication authentication ,@RequestParam("idPost") Long idPost ) {
			UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
		 User U = userRepository.findByUsername(U1.getUsername())
		            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
		 postService.updatePost(U.getId(), idPost, p);
	 }
////ok
	@GetMapping("/listeposts")
	@ResponseBody
	List<Post> listedePosts( Authentication authentication){
		UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
	    User U = userRepository.findByUsername(U1.getUsername())
	            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
		return postService.listepost(U.getId());
	}
	//ok
	@RequestMapping(value = "/getpost/{id}")
	public Optional<Post> getPost(@PathVariable Long id) {
	
		Optional<Post> p= postService.getPost(id);
				return p;
	}	
	
///ok
	@GetMapping("/listepoststags")
	
	public List<Post> getPostBytag(@RequestParam("tags") String tags) {
		
		List<Post>  g=postService.rechTags(tags);
	

				return g;
	}	
	//ok
@GetMapping("/nubpostbytag")
	
	public int getNumPostBytag(@RequestParam("tags") String tags) {
		List<Post>  g=postService.rechTags(tags);
				return g.size();
	}
///ok	
@GetMapping("/rate/{idPublication}/{rate}")
	public Float rate(@PathVariable("idPublication")Long idPublication,
			Authentication authentication,
			@PathVariable("rate")RatePub rate) {
	UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
    User U = userRepository.findByUsername(U1.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
		//return postService.rate(idPublication,idUser,rate);
	 System.out.print("debut"+rate);
	Post p= postService.rate(idPublication,U.getId(),rate);
	 System.out.print("end");
	 return p.getScore(); 
	}

///ok
@GetMapping("/listepostssanscom")
@ResponseBody
List<Post> listedePostsanscom(){
	return postService.postUnactiveComment();
}

@DeleteMapping("/deletepostsanscom")
public void suppPostsanscom(){
	postService.removeUnactiveComment();
}

///ook
@GetMapping("/listepostsbyscore")
@ResponseBody
List<Post> listedePostsByScore(){
	return postService.getAllPostsByScore();
}
//okk
@GetMapping("/postbyscore")
@ResponseBody
public Post PostByScore(){
	return postService.maxScorePost();
}
@GetMapping("/postbyCountCom")
@ResponseBody
public Post PostByCountCom(){
	return postService.maxComPost();
}
///ok
@RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
public void deletePost(@PathVariable Long id) {
	 postService.deletePost(id);
	 	 
}
@GetMapping("/listepostsaveccom")
@ResponseBody
List<Post> listedePostsAvecCom(){
	return postService.getAllPostsAvecCom();
}
@GetMapping("/listepostskey")

public List<Post> getPostBykey(@RequestParam("key") String key) {
	
	List<Post>  g=postService.searchKeyWord(key);


			return g;
}	
}
