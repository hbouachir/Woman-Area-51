package tn.esprit.spring.womanarea51.services;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;

import tn.esprit.spring.womanarea51.entities.Comment;







public interface ICommService {
	public List<Comment> getAllCommentaires() ;
	public List<Comment> getCommentaire(Long idUser,Long idPost);
	public void addCommentaire(Comment post, Long idUser, Long idPost) ;
	public void updateCommentaire(Long idUser, Long idPost, Comment post) ;
	public void deleteCommentaire(Long id) ;
	public int deletecommentaireByIdPostUser(Long comId,Long postId,Long userId);
	public List <Comment> listeCommentaire(Long idUser);
	public List <Comment> listeCommentairep(Long idPost);
	///
	public void addSousCommentaire(Comment c, Long idUser,Long idComm);
	public List<Comment> listeSousComment(Long idCom);
	public int countComByUser(Long idUser);
	public int countComByPost(Long idPost);
}

