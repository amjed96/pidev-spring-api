package tn.esprit.pidev.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.model.Role;
import tn.esprit.pidev.repository.RoleRepository;

@Data
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }
    public Iterable<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    public void deleteRole(final Long id) {
        roleRepository.deleteById(id);
    }
}
