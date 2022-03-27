package tn.esprit.spring.womanarea51;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tn.esprit.spring.womanarea51.entities.ERole;
import tn.esprit.spring.womanarea51.entities.Role;
import tn.esprit.spring.womanarea51.repositories.RoleRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.IRoleService;
import tn.esprit.spring.womanarea51.entities.User;

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


    @PostConstruct
    public void init(){
        iroleservice.initRoles();


        //THIS PART OF CODE SHOULD BE REMOVED IN DEPLOYMENT
        if (true) {
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
                        "user@mail.com",
                        encoder.encode("changeme"
                        ), "user", "luser", "user", null, "22222222", null);
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
        }

    }
}
