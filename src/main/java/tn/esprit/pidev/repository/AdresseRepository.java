package tn.esprit.pidev.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.model.Adresse;

@Repository
public interface AdresseRepository extends CrudRepository<Adresse, Long> {
}
