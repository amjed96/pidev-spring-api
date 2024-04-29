package tn.esprit.pidev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Emplacement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomEmpl;
    private Type type;
    // ADRESSE
    @OneToOne(cascade = CascadeType.ALL)
    private Adresse adresse;
    // USERS
    @OneToMany(
            mappedBy = "emplacement",
            cascade = CascadeType.ALL,
            orphanRemoval = true // TODO CHECK
            // fetch = FetchType.EAGER // TODO CHECK : or .LAZY
    )
    private List<UserEntity> users = new ArrayList<>();
    // DEPARTEMENTS
    @OneToMany(
            mappedBy = "emplacement",
            cascade = CascadeType.ALL,
            orphanRemoval = true // TODO CHECK
            // fetch = FetchType.EAGER // TODO CHECK : or .LAZY
    )
    @JsonIgnore
    private List<Departement> departements = new ArrayList<>();

    // HELPERS METHODS
    public void addUser(UserEntity user) {
        users.add(user);
        user.setEmplacement(this);
    }
    public void removeUser(UserEntity user) {
        users.remove(user);
        user.setEmplacement(null);
    }
    public void addDepartment(Departement departement) {
        departements.add(departement);
        departement.setEmplacement(this);
    }
    public void removeDepartment(Departement departement) {
        departements.remove(departement);
        departement.setEmplacement(null);
    }
}

