package tn.esprit.pidev.dto;

import lombok.Data;
import tn.esprit.pidev.model.Role;

@Data
public class AddUserDTO {
    private String nom;
    private String prenom;
    private String email;
    private String username;
    private String password;
    private String role;
    private Long emplacement;
}
