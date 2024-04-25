package tn.esprit.pidev.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String nomDep;
    // EMPLACEMENT
    @ManyToOne(
            // cascade = CascadeType.ALL
    )
    @JoinColumn(name = "emplacement_id", nullable = false)
    private Emplacement emplacement;
}
