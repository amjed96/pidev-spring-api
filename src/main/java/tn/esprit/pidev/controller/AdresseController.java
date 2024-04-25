package tn.esprit.pidev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.model.Adresse;
import tn.esprit.pidev.service.AdresseService;

@RestController
public class AdresseController {

    @Autowired
    private AdresseService adresseService;

    // CREATE
    @PostMapping("/adresse/add")
    public Adresse createAdresse(@RequestBody Adresse adresse) {
        return adresseService.saveAdresse(adresse);
    }
    // DELETE
    @GetMapping("/adresse/delete/{id}")
    public void deleteAdresse(@PathVariable("id") final Long id) {
        adresseService.deleteAdresse(id);
    }
}
