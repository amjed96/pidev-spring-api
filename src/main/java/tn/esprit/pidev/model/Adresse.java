package tn.esprit.pidev.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pays;
    private String gouvernorat;
    private String ville;
    private String rue;
    private int codePostal;
}
