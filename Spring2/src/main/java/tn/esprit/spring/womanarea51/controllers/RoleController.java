package tn.esprit.spring.womanarea51.controllers;


import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("addRole")
    public Role addUser(@RequestBody Role r){
        return rs.addRole(r);


    }

    @DeleteMapping("deleteRole")
    public void deleteRole(@RequestParam("roleId") long roleId){
        rs.DeleteRole(roleId);

    }

    @PutMapping("updateRole")
    public Role updateRole(@RequestBody Role r){
        return rs.UpdateRole(r);
    }





    @GetMapping("{roleId}")
    public Role showRole(@PathVariable("roleId") long roleId ){

        return rs.ShowRole(roleId);

    }


    @GetMapping("/roles")
    public List<Role> showAllRole(){

        return rs.ShowAllRole();

    }
}
