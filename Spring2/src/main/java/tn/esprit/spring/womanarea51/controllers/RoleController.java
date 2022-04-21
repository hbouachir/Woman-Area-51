package tn.esprit.spring.womanarea51.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.womanarea51.entities.Role;
import tn.esprit.spring.womanarea51.services.IRoleService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired

    IRoleService rs;

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN') or hasRole('ROLE_ADMIN')")
    @PostMapping("addRole")
    public Role addRole(@RequestBody Role r){
        return rs.addRole(r);


    }


    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("deleteRole")
    public void deleteRole(@RequestParam("roleId") long roleId){
        rs.DeleteRole(roleId);

    }
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN') or hasRole('ROLE_ADMIN')")

    @PutMapping("updateRole")
    public Role updateRole(@RequestBody Role r){
        return rs.UpdateRole(r);
    }




    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN') or hasRole('ROLE_ADMIN')")
    @GetMapping("{roleId}")
    public Role showRole(@PathVariable("roleId") long roleId ){

        return rs.ShowRole(roleId);

    }

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN') or hasRole('ROLE_ADMIN')")

    @GetMapping("/roles")
    public List<Role> showAllRole(){

        return rs.ShowAllRole();

    }
}