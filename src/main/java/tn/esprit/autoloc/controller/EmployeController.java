
package tn.esprit.autoloc.controller;

import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.EmployeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {

    private final EmployeService employeService;

    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }

    // READ ALL
    @GetMapping
    public List<Employe> getAllEmployes() {
        return employeService.getAllEmployes();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Employe> getEmployeById(
            @PathVariable Long id) {

        return employeService.getEmployeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public Employe createEmploye(@RequestBody Employe employe) {
        return employeService.saveEmploye(employe);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Employe> updateEmploye(
            @PathVariable Long id,
            @RequestBody Employe updatedEmploye) {

        return employeService.getEmployeById(id)
                .map(existing -> {
                    existing.setNom(updatedEmploye.getNom());
                    existing.setPrenom(updatedEmploye.getPrenom());
                    existing.setRole(updatedEmploye.getRole());

                    return ResponseEntity.ok(
                            employeService.saveEmploye(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmploye(
            @PathVariable Long id) {

        if (employeService.getEmployeById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        employeService.deleteEmploye(id);
        return ResponseEntity.noContent().build();
    }
}
