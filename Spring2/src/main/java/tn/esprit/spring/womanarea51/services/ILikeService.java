package tn.esprit.spring.womanarea51.services;

public interface ILikeService {
	boolean addLike(Long comId, Long IdUser) ;

	public int getAllLikesForCom(Long comId);
}
