package tn.esprit.spring.womanarea51.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.spring.womanarea51.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	Optional<User> findByTel(String tel);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	Boolean existsByTel(String tel);

	@Query(value = "SELECT u.`user_id` FROM `user` u join user_roles r on u.`user_id`=r.user_id WHERE r.role_id=1 ", nativeQuery = true)
	public List<String> findClient_pt_100(int nbpoint);



	/* liste des users */
	@Query(value = "SELECT user_id FROM user_roles WHERE role_id=1", nativeQuery = true)
	public List<Long> ListeUsers();
	
	@Modifying
	@Query(value = "UPDATE `user` SET `etat`=?1 WHERE `user_id`=?2", nativeQuery = true)
	public void ConfirmerLiv(String etat1, long id);
	

	
	/* Selon Sexe */
	@Query(value = "SELECT COUNT(*) FROM user WHERE `sexe`=?1", nativeQuery = true)
	public int NombreUsersSelonSexe(String Sexe);



	@Query(value = "SELECT * FROM `user` WHERE `username` LIKE ?1%", nativeQuery = true)
	public List<User> getUserSelonUsername(String cle);



	@Query(value = "SELECT * FROM `user` WHERE `email` LIKE ?1%", nativeQuery = true)
	public List<User> getUserSelonEmail(String cle);



	@Query(value = "SELECT * FROM `user`  ORDER BY `signup_day` DESC LIMIT 5", nativeQuery = true)
	public List<User> getNewUsers();




	@Query(value = "SELECT COUNT(*) FROM user ", nativeQuery = true)
	public int NombreUsers();
	
	



	//point fidelité
	@Query(value = "SELECT count(*) from user where point_fidelite <100 ",nativeQuery = true)
	public int nombreUsersbyPointfideletInf100();
	@Query(value = " SELECT count(*) from user where point_fidelite BETWEEN 100 and 300  ",nativeQuery = true)
	public int nombreUsersbyPointfideletbetwen100et300();
	@Query(value = " SELECT count(*)from user where point_fidelite >=300",nativeQuery=true)
	public int nombreUsersbyPointfideletSup300();
	@Query(value = "SELECT Avg(`point_fidelite`) FROM user",nativeQuery=true)
	public float moyenneNpointFidelet();

	User findByFirstName(String username);

}
