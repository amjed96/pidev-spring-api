package tn.esprit.pidev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pidev.model.Role;
import tn.esprit.pidev.service.RoleService;

@RestController
public class RoleController {

    @Autowired
    RoleService roleService;

    // GET
    @GetMapping("/role")
    public Iterable<Role> getRoles() {
        return roleService.getAllRoles();
    }
    // POST
    @PostMapping("/role/add")
    public Role addRole(Role role) {
        return roleService.addRole(role);
    }
    // DELETE
    @DeleteMapping("/role/delete/{id}")
    public void deleteRole(final Long id) {
        roleService.deleteRole(id);
    }
    // TODO GET PERMISSIONS OF ROLE # TRY GRANTED_AUTHORITIES
}
