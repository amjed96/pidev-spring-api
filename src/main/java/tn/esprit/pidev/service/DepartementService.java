package tn.esprit.pidev.service;

import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.dto.CreateDepartmentDTO;
import tn.esprit.pidev.dto.UpdateDepartementDTO;
import tn.esprit.pidev.model.Departement;
import tn.esprit.pidev.model.Emplacement;
import tn.esprit.pidev.repository.DepartementRepository;
import tn.esprit.pidev.repository.EmplacementRepository;

import java.util.Optional;

@Log
@Data
@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private EmplacementRepository emplacementRepository;

    public Optional<Departement> getDepartement(final Long id) {
        return departementRepository.findById(id);
    }
    public void deleteDepartement(final Long id) {
        departementRepository.deleteById(id);
    }
    public Departement saveDepartement(CreateDepartmentDTO departementrequest) {
        Departement departement = new Departement(departementrequest.getNomDep());
        Emplacement emplacement = emplacementRepository.findById(departementrequest.getEmplacement()).orElse(null);

        if (emplacement != null) {
            emplacement.addDepartment(departement);
            emplacementRepository.save(emplacement);
            Departement savedDepartement = departementRepository.save(departement);
            return savedDepartement;
        }
        return null;
    }
    public Departement updateDepartement(UpdateDepartementDTO departementrequest, final Long id) {
        Departement oldDep = departementRepository.findById(id).orElse(null);
        Departement departement = new Departement(departementrequest.getNomDep());
        departement.setId(id);
        departement.setEmplacement(oldDep.getEmplacement());
        // log.info("TEST: "+departement.toString());
        return departementRepository.save(departement);
    }
}
