package tn.esprit.pidev.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.model.Departement;

@Repository
public interface DepartementRepository extends CrudRepository<Departement, Long> {
}
