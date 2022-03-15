package tn.esprit.spring.womanarea.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.spring.womanarea.demo.Entities.User;

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

	/* liste des livreur */
	@Query(value = "SELECT user_id FROM user_roles WHERE role_id=4", nativeQuery = true)
	public List<Long> Listettlivreur();

	/* liste des users */
	@Query(value = "SELECT user_id FROM user_roles WHERE role_id=1", nativeQuery = true)
	public List<Long> ListeUsers();
	
	@Modifying
	@Query(value = "UPDATE `user` SET `etat`=?1 WHERE `user_id`=?2", nativeQuery = true)
	public void ConfirmerLiv(String etat1, long id);
	
	@Query(value = "SELECT `user_id` FROM `user` WHERE `etat`=?1 and `disponible`=?2 and `lieux_travail`=?3 ORDER BY RAND() LIMIT 1", nativeQuery = true)
	public Long findparhasard(String etat,String dispo,String lieuxTravail);	
	
	/* Selon Sexe */
	@Query(value = "SELECT COUNT(*) FROM user WHERE `sexe`=?1", nativeQuery = true)
	public int NombreUsersSelonSexe(String Sexe);
	/* Change disponibilité livreur */
	@Modifying
	@Query(value = "UPDATE `user` SET `disponible`=?1 WHERE `user_id`=?2", nativeQuery = true)
	public void ChangeDispo(String Dispo, long id);
	//Top 10 Stat liv
	@Query(value = "SELECT `user_id` FROM `user`  ORDER BY `nb_mission` DESC LIMIT 10", nativeQuery = true)
	public List<Long> Top10nbLivreur();
	
	@Query(value = "SELECT * FROM `user` WHERE `username` LIKE ?1%", nativeQuery = true)
	public List<User> getUserSelonChoix(String cle);
	@Query(value = "SELECT * FROM `user` WHERE `email` LIKE ?1%", nativeQuery = true)
	public List<User> getUserSelonEmail(String cle);
	
	@Query(value = "SELECT * FROM `user`  ORDER BY `signup_day` DESC LIMIT 8", nativeQuery = true)
	public List<User> getNewUsers();
	
	@Query(value = "SELECT COUNT(*) FROM user ", nativeQuery = true)
	public int NombreUsers();
	
	
    //Charite Event 
	@Query(value = "SELECT e.nbplace FROM t_events as e WHERE e.id=?1", nativeQuery = true)
	public int NombrePlacesEvent(Long idevent);
	@Query(value = "SELECT e.nbparticipant FROM t_events as e WHERE e.id=?1", nativeQuery = true)
	public int NombreParticpEvent(Long idevent);
	@Query(value = "SELECT DISTINCT e.id FROM t_events as e", nativeQuery = true)
	public List<Long> EventList();
	
	@Query(value = "SELECT SUM(salaire_brut) FROM `salaire`", nativeQuery = true)
	public float TotalSalaries();

	@Query(value = "SELECT count(*) from user where point_fidelite <100 ",nativeQuery = true)
	public int nombreUsersbyPointfideletInf100();
    //SELECT * FROM `user`  ORDER BY `signup_day` DESC LIMIT 3
	@Query(value = " SELECT count(*) from user where point_fidelite BETWEEN 100 and 300  ",nativeQuery = true)
	public int nombreUsersbyPointfideletbetwen100et300();
	@Query(value = " SELECT count(*)from user where point_fidelite >=300",nativeQuery=true)
	public int nombreUsersbyPointfideletSup300();
	@Query(value = "SELECT Avg(`point_fidelite`) FROM user",nativeQuery=true)
	public float moyenneNpointFidelet();
	//Livreur Par hasard2
	@Query(value = "SELECT `user_id` FROM `user` WHERE `etat`=?1 and `lieux_travail`=?2 ORDER BY RAND() LIMIT 1", nativeQuery = true)
	public long findparhasard2(String etat,String lieuxTravail);	
}
