package tn.esprit.spring.womanarea51.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.Filepost;
import tn.esprit.spring.womanarea51.entities.Post;
import tn.esprit.spring.womanarea51.entities.RatePub;
import tn.esprit.spring.womanarea51.entities.User;


public interface IPostService {

	public List<Post> getAllPosts() ;
	public List<Post> getAllPostsAvecCom() ;
	Post getById (Long id)throws NoSuchElementException;
	public Optional<Post> getPost(Long id);
	public void addPost(Post post, Long idUser) ;
	//
	public void updatePost(Long id,Long idPost, Post post) ;
	public void deletePost(Long id) ;
	public List<Post> listepost(Long idUser);
	public int getAllPostsForUser(Long idUser);
	public void addPostt(Post post) ;
	//public Post upPost(Post post);
	public List<Post> listepostToken(String token);
	public  List<Post> searchpost(String w);
	public List<Post> rechTags(String s);
	////
	public Post rate(Long idPost, Long idUser, RatePub rate);
	public List<Post> postUnactiveComment();
	public void removeUnactiveComment();
	public List<Post> getAllPostsByScore() ;
	public Post maxScorePost();
	public Post maxComPost();
	// public String uploadFile(MultipartFile uploadFile);
	public Post addPostImageTest( MultipartFile file, Long idPost);
public List<Post> searchKeyWord(String word);
///angular
public Post upPost(Post post);
public User getUser(Long id);

public String urlFilePost(Long idPost);
public Filepost urlFilePostt(Long idPost);

}
