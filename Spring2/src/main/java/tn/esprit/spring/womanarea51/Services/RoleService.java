package tn.esprit.spring.womanarea51.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.womanarea51.Entities.ERole;
import tn.esprit.spring.womanarea51.Entities.Role;
import tn.esprit.spring.womanarea51.Repositories.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    RoleRepository rr;

    @Override
    public Role addRole(Role r) {
        // TODO Auto-generated method stub
        return rr.save(r);
    }

    @Override
    public Role ShowRole(long id) {
        // TODO Auto-generated method stub
        return rr.findById(id).orElse(null);
    }

    @Override
    public Role UpdateRole(Role r) {
        // TODO Auto-generated method stub
        return rr.save(r);
    }

    @Override
    public void DeleteRole(long id) {
        rr.deleteById(id);
    }

    @Override
    public List<Role> ShowAllRole() {
        // TODO Auto-generated method stub
        return (List<Role>) rr.findAll();
    }

    @Override
    public void initRoles() {
        List<Role> available_roles = rr.findAll();
        List<ERole> roles = new ArrayList<ERole>();
        for (Role role: available_roles){
            roles.add(role.getName());
        }
        for (ERole erole: ERole.values()){
            if (!roles.contains(erole)){
                rr.save(new Role(erole));
            }
        }
    }

}
