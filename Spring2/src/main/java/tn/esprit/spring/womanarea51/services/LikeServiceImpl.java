package tn.esprit.spring.womanarea51.services;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.womanarea51.entities.Comment;
import tn.esprit.spring.womanarea51.entities.Like;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.CommRepository;
import tn.esprit.spring.womanarea51.repositories.LikeRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;

@Service
public class LikeServiceImpl implements ILikeService {

	@Autowired
	LikeRepository likeRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CommRepository commentaireRepository;
	
	@Override
	public boolean addLike(Long comId, Long IdUser) {
		 Comment com = this.commentaireRepository.findById(comId).orElse(null);
	        User user = this.userRepository.findById(IdUser).orElse(null);

	 
	        Like likeByUserAndCom = this.likeRepository.findByUserlikeAndComment(user, com);

	        if (likeByUserAndCom == null) {
	        	Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
	        	
	            Like like = new Like();
	            like.setLikeDate(currentTimestamp);
	            like.setUserlike(user);
	            like.setComment(com);
	            like.setCountLike(1);
             com.setCountlike(com.getCountlike()+1);
	            return this.likeRepository.save(like) != null;
	        }
		return false;
	}

	@Override
	public int getAllLikesForCom(Long comId) {
		// TODO Auto-generated method stub
        Comment com = this.commentaireRepository.findById(comId).orElse(null);

        return this.likeRepository.findAllByComment(com).size();

	}

}
