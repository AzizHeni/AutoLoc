
package tn.esprit.autoloc.controller;

import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.ContratService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
public class ContratController {

    private final ContratService contratService;

    public ContratController(ContratService contratService) {
        this.contratService = contratService;
    }

    // READ ALL
    @GetMapping
    public List<Contrat> getAllContrats() {
        return contratService.getAllContrats();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Contrat> getContratById(
            @PathVariable Long id) {

        return contratService.getContratById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public Contrat createContrat(@RequestBody Contrat contrat) {
        return contratService.saveContrat(contrat);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Contrat> updateContrat(
            @PathVariable Long id,
            @RequestBody Contrat updatedContrat) {

        return contratService.getContratById(id)
                .map(existing -> {
                    existing.setDateSignature(
                            updatedContrat.getDateSignature());
                    existing.setDateSignature(updatedContrat.getDateSignature());
                    existing.setReservation(
                            updatedContrat.getReservation());

                    return ResponseEntity.ok(
                            contratService.saveContrat(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContrat(
            @PathVariable Long id) {

        if (contratService.getContratById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        contratService.deleteContrat(id);
        return ResponseEntity.noContent().build();
    }
}
