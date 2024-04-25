package tn.esprit.pidev.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.model.Adresse;
import tn.esprit.pidev.repository.AdresseRepository;

import java.util.Optional;

@Data
@Service
public class AdresseService {

    @Autowired
    private AdresseRepository adresseRepository;

    public Optional<Adresse> getAdresse(final Long id) {
        return adresseRepository.findById(id);
    }
    public void deleteAdresse(final Long id) {
        adresseRepository.deleteById(id);
    }
    public Adresse saveAdresse(Adresse adresse) {
        Adresse savedAdresse = adresseRepository.save(adresse);
        return savedAdresse;
    }
}
