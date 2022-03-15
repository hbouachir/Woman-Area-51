package tn.esprit.spring.womanarea.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.womanarea.demo.Entities.User;
import tn.esprit.spring.womanarea.demo.Entities.VerificationToken;
import tn.esprit.spring.womanarea.demo.Repositories.UserRepository;
import tn.esprit.spring.womanarea.demo.Repositories.VerificationTokenRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    private VerificationTokenRepository tokenRepository;
	
	/*Chercher un utilisateur*/
	public User findOne(long id){
	return userRepository.findById(id).get();
	}
	public User save(User u) {
		return userRepository.save(u);
	}
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	/*Update d'un user*/
	public  User updateUser(User user)	{
		return userRepository.save(user);
		
	}
	
	/*get all Users*/
	public List<User> getAllUsers(){
		 List<Long> listUsersId=userRepository.ListeUsers();
	
			List<User> listUsers = new ArrayList();
		 User u = new User();
		 for(Long  a : listUsersId)
		 {
			 u=findOne(a);
			// if(u.getEtatAcc().equals("waiting"))
			// {
				 listUsers.add(u);
			// }
		 }
		return  listUsers;		
	}
	
	public User getUser(String verificationToken) {
        User user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }
	
	public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }
	
	public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }
	/*Livreur methodes oussama*/
	
  
	public int getNombresUsersSelonSexe(String sexe)
	{
		return userRepository.NombreUsersSelonSexe(sexe);
	}
	
	public List<User> getUserSelonChoix(String choix, String cle)
	{
		return userRepository.getUserSelonChoix(cle);
	}
	public List<User> getUserSelonEmail(String choix, String cle)
	{
		return userRepository.getUserSelonEmail(cle);
	}
	/***ayed***/
	public int getmbreUsersbyPointfideletInf100(){
		return userRepository.nombreUsersbyPointfideletInf100();
	};
	public int getmbreUsersbyPointfideletBetwen100300(){
		return userRepository.nombreUsersbyPointfideletbetwen100et300();
	};
	
	public int getmbreUsersbyPointfideletSup(){
		return userRepository.nombreUsersbyPointfideletSup300();
	};
	public int nbuser() {
		return userRepository.findAll().size();
	}
	public float moyennenbpointfiedelete(){
		return userRepository.moyenneNpointFidelet();
	}
	/***ayed**/
}
