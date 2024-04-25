package tn.esprit.pidev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.model.Adresse;
import tn.esprit.pidev.model.Departement;
import tn.esprit.pidev.model.Emplacement;
import tn.esprit.pidev.model.UserEntity;
import tn.esprit.pidev.service.AdresseService;
import tn.esprit.pidev.service.EmplacementService;

@RestController
public class EmplacementController {

    @Autowired
    private EmplacementService emplacementService;

    // TODO GET ALL EMPLACEMENTS
    @GetMapping("/emplacement")
    public Iterable<Emplacement> getAllEmplacements() {
        return emplacementService.getAllEmplacements();
    }

    // GET V
    @GetMapping("/emplacement/{id}")
    public Emplacement getEmplacement(@PathVariable("id") final Long id) {
        return emplacementService.getEmplacement(id).isPresent()
                ? emplacementService.getEmplacement(id).get() : null;
    } // TODO FIX
    // CREATE V
    @PostMapping("/emplacement/add")
    public Emplacement /*Response*/ addEmplacement(@RequestBody Emplacement emplacement) {
        return emplacementService.saveEmplacement(emplacement);
    }
    // UPDATE V
    @PutMapping("/emplacement/update/{id}")
    public Emplacement updateEmplacement(@RequestBody Emplacement emplacement, @PathVariable("id") final Long id) {
        emplacement.setId(id);
        return emplacementService.saveEmplacement(emplacement);
    }
    // GET USERS V:add_values
    // TODO ADD DTO
    @GetMapping("/emplacement/{id}/get-users")
    public Iterable<UserEntity> getUsers(@PathVariable("id") final Long id) {
        Emplacement emplacement = emplacementService.getEmplacement(id).get();
        return emplacement.getUsers();
    }
    // GET DEPARTMENTS V:add_values
    @GetMapping("/emplacement/{id}/get-departments")
    public Iterable<Departement> getDepartments(@PathVariable("id") final Long id) {
        Emplacement emplacement = emplacementService.getEmplacement(id).get();
        return emplacement.getDepartements();
    }
    // GET ADRESSE V
    @GetMapping("/emplacement/{id}/get-adresse")
    public Adresse getAdresse(@PathVariable("id") final Long id) {
        Emplacement emplacement = emplacementService.getEmplacement(id).get();
        return emplacement.getAdresse();
    }
    // ADD ADRESSE V
    @PostMapping("/emplacement/{id}/add-adresse")
    public Emplacement addAdresse(@PathVariable("id") final Long id, @RequestBody Adresse adresse) {
        return emplacementService.addAdresse(id, adresse);
    }
    // REMOVE ADRESSE V
    @DeleteMapping("/emplacement/{id}/delete-adresse")
    public Emplacement deleteAdresse(@PathVariable("id") final Long id) {
        return emplacementService.deleteAdresse(id);
    }
}
