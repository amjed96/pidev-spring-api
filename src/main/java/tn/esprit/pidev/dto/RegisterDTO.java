package tn.esprit.pidev.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String nom;
    private String prenom;
    private String email;
    private String username;
    private String password;
    private String nomEmpl;
    private String paysEmpl;
    private String gouvernoratEmpl;
    private String villeEmpl;
    private String rueEmpl;
    private int codePostalEmpl;
}
