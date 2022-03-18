package tn.esprit.spring.womanarea.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.womanarea.demo.Entities.Role;
import tn.esprit.spring.womanarea.demo.Entities.User;
import tn.esprit.spring.womanarea.demo.Entities.VerificationToken;
import tn.esprit.spring.womanarea.demo.Repositories.RoleRepository;
import tn.esprit.spring.womanarea.demo.Repositories.UserRepository;
import tn.esprit.spring.womanarea.demo.Repositories.VerificationTokenRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService implements IUserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
    private VerificationTokenRepository tokenRepository;
	
	/*Chercher un utilisateur*/

	public User findOne(long id){
	return userRepository.findById(id).get();
	}
	@Override
	public User save(User u) {
		return userRepository.save(u);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	/*Update d'un user*/
	@Override
	public  User updateUser(User user)	{
		return userRepository.save(user);
		
	}

	@Transactional
	@Override
	public void addUserAffectRole(long idRole, User u) {
		Role r=roleRepository.findById(idRole).orElse(null);
		if (r!=null && !u.getRoles().contains(r)){
			u.addRole(r);
			userRepository.save(u);

		}

	}



	/*Delete user */
	@Override
	public  void deleteUser(long id)	{
		 userRepository.deleteById(id);

	}
	
	/*get all Users by Role*/
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

  
	public int getNombresUsersSelonSexe(String sexe)
	{
		return userRepository.NombreUsersSelonSexe(sexe);
	}
	
	public List<User> getUserSelonUsername( String username)
	{
		return userRepository.getUserSelonUsername(username);
	}
	public List<User> getUserSelonEmail(String email)
	{
		return userRepository.getUserSelonEmail(email);
	}
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
}
