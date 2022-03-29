package tn.esprit.spring.womanarea51.services;

public interface ILikeService {
	boolean addLike(Long comId, Long IdUser) ;
	public boolean addLikeSouCom(Long comId, Long IdUser);
	public int getAllLikesForCom(Long comId);
	public boolean deleteLikeSouCom(Long comId, Long IdUser);
}
