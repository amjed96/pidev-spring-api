package tn.esprit.pidev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.dto.CreateDepartmentDTO;
import tn.esprit.pidev.dto.UpdateDepartementDTO;
import tn.esprit.pidev.model.Departement;
import tn.esprit.pidev.service.DepartementService;

@RestController
public class DepartementController {

    @Autowired
    private DepartementService departementService;

    // GET V
    @GetMapping("/departement/{id}")
    public Departement getDepartement(@PathVariable("id") final Long id) {
        return departementService.getDepartement(id).isPresent()
                ? departementService.getDepartement(id).get()
                : null;
    }
    // CREATE V
    @PostMapping("/departement/add")
    public Departement addDepartement(@RequestBody CreateDepartmentDTO departement) {
        return departementService.saveDepartement(departement);
    }
    // UPDATE V
    @PutMapping("/departement/update/{id}")
    public Departement updateDepartement(@RequestBody UpdateDepartementDTO departement, @PathVariable("id") final Long id) {
        return departementService.updateDepartement(departement, id);
    }
    // DELETE V
    @DeleteMapping("/departement/delete/{id}")
    public void deleteDepartement(@PathVariable("id") final Long id) {
        departementService.deleteDepartement(id);
    }
}
