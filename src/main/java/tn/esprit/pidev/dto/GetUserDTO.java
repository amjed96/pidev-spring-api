package tn.esprit.pidev.dto;

import lombok.Data;
import tn.esprit.pidev.model.Emplacement;
import tn.esprit.pidev.model.Role;

import java.util.List;

@Data
public class GetUserDTO {
    private String nom;
    private String prenom;
    private String email;
    private String username;
    private List<Role> roles;
    private Emplacement emplacement;
}
