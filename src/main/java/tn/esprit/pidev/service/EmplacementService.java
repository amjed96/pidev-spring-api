package tn.esprit.pidev.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.model.Adresse;
import tn.esprit.pidev.model.Emplacement;
import tn.esprit.pidev.repository.AdresseRepository;
import tn.esprit.pidev.repository.EmplacementRepository;

import java.util.Optional;

@Data
@Service
public class EmplacementService {

    @Autowired
    private EmplacementRepository emplacementRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    public Iterable<Emplacement> getAllEmplacements() {
        return this.emplacementRepository.findAll();
    }

    public Optional<Emplacement> getEmplacement(final Long id) {
        return emplacementRepository.findById(id);
    }
    /*public void deleteEmplacement(final Long id) {
        emplacementRepository.deleteById(id);
    }*/
    public Emplacement saveEmplacement(Emplacement emplacement) {
        Emplacement savedEmplacement = emplacementRepository.save(emplacement);
        return savedEmplacement;
    }
    public Emplacement addAdresse(final Long id, Adresse adresse) {
        Emplacement emplacement = emplacementRepository.findById(id).orElse(null);
        if (emplacement != null) {
            emplacement.setAdresse(adresseRepository.save(adresse));
            return emplacementRepository.save(emplacement);
        }
        return null;
    }
    public Emplacement deleteAdresse(final Long id) {
        Emplacement emplacement = emplacementRepository.findById(id).orElse(null);
        if (emplacement != null) {
            Adresse adresse = emplacement.getAdresse();
            emplacement.setAdresse(null);
            adresseRepository.delete(adresse);
            return emplacement;
        }
        return null;
    }
}
