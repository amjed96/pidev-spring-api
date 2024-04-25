package tn.esprit.pidev.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.model.Emplacement;

@Repository
public interface EmplacementRepository extends CrudRepository<Emplacement, Long> {
}
