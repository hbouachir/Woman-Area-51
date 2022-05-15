package tn.esprit.spring.womanarea51;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tn.esprit.spring.womanarea51.entities.ERole;
import tn.esprit.spring.womanarea51.entities.Role;
import tn.esprit.spring.womanarea51.repositories.RoleRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.IRoleService;
import tn.esprit.spring.womanarea51.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Monitor {
    @Autowired
    IRoleService iroleservice;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private DataSource dataSource;


    @PostConstruct
    public void init(){
        iroleservice.initRoles();

        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false, false, "UTF-8", new ClassPathResource("quartz_tables.sql"));
        resourceDatabasePopulator.execute(dataSource);


        //THIS PART OF CODE SHOULD BE REMOVED IN DEPLOYMENT
        if (true) {
            if (!userRepository.existsByUsername("superuser")) {
                Set<Role> roles = new HashSet<>();
                Role superRole = roleRepository.findByName(ERole.ROLE_SUPER_USER).orElse(null);
                roles.add(superRole);
                //create admin account
                User user = new User("superuser",
                        "superuser@mail.com",
                        encoder.encode("changeme"
                        ), "super", "user", "admin", null, "11111111", null);
                user.setRoles(roles);
                user.setEnabled(true);
                userRepository.save(user);
            }
            if (!userRepository.existsByUsername("admin")) {
                Set<Role> rolesa = new HashSet<>();
                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElse(null);
                rolesa.add(adminRole);
                //create admin account
                User user = new User("admin",
                        "admin@mail.com",
                        encoder.encode("changeme"
                        ), "admin", "ladmin", "admin", null, "11111111", null);
                user.setRoles(rolesa);
                user.setEnabled(true);
                userRepository.save(user);
            }
            if (!userRepository.existsByUsername("user")) {
                Set<Role> roles = new HashSet<>();
                Role adminRole = roleRepository.findByName(ERole.ROLE_USER).orElse(null);
                roles.add(adminRole);
                //create admin account
                User user = new User("user",
                        "nadaazzabi1@gmail.com",
                        encoder.encode("changeme"
                        ), "user", "luser", "user", null, "22222222", null);
                user.setRoles(roles);
                user.setEnabled(true);
                userRepository.save(user);
            }
            if (!userRepository.existsByUsername("Thralxl")) {
                Set<Role> roles = new HashSet<>();
                Role adminRole = roleRepository.findByName(ERole.ROLE_USER).orElse(null);
                roles.add(adminRole);
                //create admin account
                User user = new User("Thralxl",
                        "thralxl25@gmail.com",
                        encoder.encode("changeme"
                        ), "Thralxl", "Thralxl", "Thralxl", null, "22222222", null);
                user.setRoles(roles);
                user.setEnabled(true);
                userRepository.save(user);
            }
            if (!userRepository.existsByUsername("expert1")) {
                Set<Role> roles = new HashSet<>();
                Role adminRole = roleRepository.findByName(ERole.ROLE_EXPERT).orElse(null);
                adminRole.setExpertField("fights");

                roles.add(adminRole);
                //create admin account
                User user = new User("expert1",
                        "expert1@mail.com",
                        encoder.encode("changeme"
                        ), "expert1", "lexpert1", "expert1", null, "7777777", null);
                adminRole.setExpertField("Harrassment");
                user.setRoles(roles);
                user.setEnabled(true);
                userRepository.save(user);
            }
            if (!userRepository.existsByUsername("expert2")) {
                Set<Role> roles = new HashSet<>();
                Role adminRole = roleRepository.findByName(ERole.ROLE_EXPERT).orElse(null);
                adminRole.setExpertField("Divorce");
                roles.add(adminRole);
                //create admin account
                User user = new User("expert2",
                        "expert2@mail.com",
                        encoder.encode("changeme"
                        ), "expert2", "lexpert2", "expert2", null, "6666666", null);
                user.setRoles(roles);
                user.setEnabled(true);
                userRepository.save(user);
            }
            if (!userRepository.existsByUsername("expert3")) {
                Set<Role> roles = new HashSet<>();
                Role adminRole = roleRepository.findByName(ERole.ROLE_EXPERT).orElse(null);
                adminRole.setExpertField("Social");
                roles.add(adminRole);
                //create admin account
                User user = new User("expert3",
                        "expert3@mail.com",
                        encoder.encode("changeme"
                        ), "expert3", "lexpert3", "expert3", null, "0000000", null);
                user.setRoles(roles);
                user.setEnabled(true);
                userRepository.save(user);
            }
            if (!userRepository.existsByUsername("sirine")) {
                Set<Role> roles = new HashSet<>();
                Role adminRole = roleRepository.findByName(ERole.ROLE_USER).orElse(null);
                roles.add(adminRole);
                //create admin account
                User user = new User("sirine",
                        "sirine@mail.com",
                        encoder.encode("changeme"
                        ), "sirine", "lsirine", "sirine", null, "54545454", null);
                user.setRoles(roles);
                user.setEnabled(true);
                userRepository.save(user);
            }
            if (!userRepository.existsByUsername("instructor")) {
                Set<Role> rolesi = new HashSet<>();
                Role adminRole = roleRepository.findByName(ERole.ROLE_INSTRUCTOR).orElse(null);
                rolesi.add(adminRole);
                //create admin account
                User user = new User("instructor",
                        "instructor@mail.com",
                        encoder.encode("changeme"
                        ), "instructor", "linstructor", "instructor", null, "11111154", null);
                user.setRoles(rolesi);
                user.setEnabled(true);
                userRepository.save(user);
            }
            if (!userRepository.existsByUsername("ainstructor")) {
                Set<Role> rolesi = new HashSet<>();
                Role adminRole = roleRepository.findByName(ERole.ROLE_INSTRUCTOR).orElse(null);
                rolesi.add(adminRole);
                //create admin account
                User user = new User("ainstructor",
                        "ainstructor@mail.com",
                        encoder.encode("changeme"
                        ), "ainstructor", "lainstructor", "ainstructor", null, "11117754", null);
                user.setRoles(rolesi);
                user.setEnabled(true);
                userRepository.save(user);
            }
            if (!userRepository.existsByUsername("abinstructor")) {
                Set<Role> rolesi = new HashSet<>();
                Role adminRole = roleRepository.findByName(ERole.ROLE_INSTRUCTOR).orElse(null);
                rolesi.add(adminRole);
                //create admin account
                User user = new User("abinstructor",
                        "abinstructor@mail.com",
                        encoder.encode("changeme"
                        ), "abinstructor", "labinstructor", "abinstructor", null, "18811154", null);
                user.setRoles(rolesi);
                user.setEnabled(true);
                userRepository.save(user);
            }
            if (!userRepository.existsByUsername("aabinstructor")) {
                Set<Role> rolesi = new HashSet<>();
                Role adminRole = roleRepository.findByName(ERole.ROLE_INSTRUCTOR).orElse(null);
                rolesi.add(adminRole);
                //create admin account
                User user = new User("aabinstructor",
                        "aabinstructor@mail.com",
                        encoder.encode("changeme"
                        ), "aabinstructor", "laabinstructor", "aabinstructor", null, "18871154", null);
                user.setRoles(rolesi);
                user.setEnabled(true);
                userRepository.save(user);
            }
        }

    }
}
