package tn.esprit.spring.womanarea51.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.womanarea51.entities.Comment;
import tn.esprit.spring.womanarea51.entities.Post;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.CommRepository;
import tn.esprit.spring.womanarea51.repositories.PostRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;




@Service
public class CommService implements ICommService {

	@Autowired
	CommRepository commRepository;
	@Autowired
	UserRepository ur;
	@Autowired
	PostRepository pr;
	

	@Override
	public List<Comment> getAllCommentaires() {
		List<Comment> Commentaires = new ArrayList<>();

		commRepository.findAll().forEach(Commentaires::add);

		return Commentaires;
	}

	@Override
	public List<Comment> getCommentaire(Long idUser, Long idPost) {
		List<Comment> comments=new ArrayList<>();
		comments= commRepository.getCommentaire(idPost, idUser);
		return comments;
	}

	@Override
	public void addCommentaire(Comment c, Long idUser, Long idPost) {
int count=0;
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		List<String> badwords = new ArrayList<>();
		badwords.add("bad");
		badwords.add("badwords");
		badwords.add("homme");
		
		String motcommentaire[] = c.getBody().split(" ");
		String com = "";
		String msg="";
		User u = ur.findById(idUser).orElse(null);
		Post p = pr.findById(idPost).orElse(null);
		int cou=u.getPointWord();
		for (String mots : motcommentaire) {
			// if(motcommentaire.length==1 && motcommentaire.equals("b"))

			if (badwords.contains(mots)) {
				mots = "(*****)";
				com = com + " " + mots;
				
					cou++;
					u.setPointWord(cou);
				
			} else
				com = com + " " + mots;
		}
		if(u.getPointWord()==7){
			
			System.out.println("7");
			
		}else if(u.getPointWord()==10){
		
			u.setEnabled(false);
		}
		int countCom=p.getCountCom();
		countCom++;
		//count=0;
		c.setUserc(u);
		c.setCreatedate(currentTimestamp);
		c.setPostc(p);
		c.setBody(com);
		p.setCountCom(countCom);
		
		commRepository.save(c);

	}

	@Override
	public void updateCommentaire(Long idUser, Long idPost, Comment c) {
             Long idCom= c.getIdCom();
             int count=0;
             if (idCom!=null){
            	
         		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
         		List<String> badwords = new ArrayList<>();
         		badwords.add("bad");
         		badwords.add("badwords");
         		badwords.add("homme");
         		
         		String motcommentaire[] = c.getBody().split(" ");
         		String com = "";
         		String msg="";
         		User u = ur.findById(idUser).orElse(null);
         		Post p = pr.findById(idPost).orElse(null);
         		int cou=u.getPointWord();
         		for (String mots : motcommentaire) {
         			// if(motcommentaire.length==1 && motcommentaire.equals("b"))

         			if (badwords.contains(mots)) {
         				mots = "(*****)";
         				com = com + " " + mots;
         				
         					cou++;
         					u.setPointWord(cou);
         				
         			} else
         				com = com + " " + mots;
         		}
         		if(u.getPointWord()==7){
         			
         			System.out.println("7");
         			
         		}else if(u.getPointWord()==10){
         		
         			u.setEnabled(false);
         		}
         		
         		//count=0;
         		c.setUserc(u);
         		c.setCreatedate(currentTimestamp);
         		c.setPostc(p);
         		c.setBody(com);
         		commRepository.save(c); 
             }
	}

	@Override
	public void deleteCommentaire(Long id) {
		Comment com = commRepository.findById(id).get();
		
			commRepository.delete(com);	 

	}
	@Override
	public int deletecommentaireByIdPostUser(Long comId,Long sujetId,Long userId) {
		Comment com = commRepository.findById(comId).get();
		if(com.getUserc().getId()== userId)
		{
			commRepository.deleteById(com.getIdCom());	 
			 return 1;
		}
			 return 0;  
	}

	@Override
	public List<Comment> listeCommentaire(Long idUser) {
		// Commentaire c = new Commentaire();

		User u = ur.findById(idUser).orElse(null);
		// Post p = pr.findById(idPost).orElse(null);

		return commRepository.findByUserc(u);
		// return null;
	}

	@Override
	public List<Comment> listeCommentairep(Long idPost) {
		Post p = pr.findById(idPost).orElse(null);

		return commRepository.findByPostc(p);
	}

	@Override
	public void addSousCommentaire(Comment c, Long idUser, Long idComm) {

		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

		User u = ur.findById(idUser).orElse(null);
		Comment p = commRepository.findById(idComm).orElse(null);
		c.setCreateR(currentTimestamp);
		c.setRcomment(p);
		c.setUsersc(u);
		

		commRepository.save(c);

	}
	
	@Override
	public List<Comment> listeSousComment(Long idCom) {
		
		Comment com = commRepository.findById(idCom).orElse(null);
		List<Comment>  s= commRepository.findByRcomment(com);
	
		return s;
	}

	@Override
	public int countComByUser(Long idUser) {
		// TODO Auto-generated method stub
		return listeCommentaire(idUser).size();
	}
	@Override
	public int countComByPost(Long idPost) {
		// TODO Auto-generated method stub
		return listeCommentaire(idPost).size();
	}
}
