
package tn.esprit.autoloc.controller;

import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.PaiementService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    private final PaiementService paiementService;

    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    // READ ALL
    @GetMapping
    public List<Paiement> getAllPaiements() {
        return paiementService.getAllPaiements();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Paiement> getPaiementById(
            @PathVariable Long id) {

        return paiementService.getPaiementById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public Paiement createPaiement(@RequestBody Paiement paiement) {
        return paiementService.savePaiement(paiement);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Paiement> updatePaiement(
            @PathVariable Long id,
            @RequestBody Paiement updatedPaiement) {

        return paiementService.getPaiementById(id)
                .map(existing -> {
                    existing.setMontant(updatedPaiement.getMontant());
                    existing.setDatePaiement(updatedPaiement.getDatePaiement());

                    return ResponseEntity.ok(
                            paiementService.savePaiement(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaiement(
            @PathVariable Long id) {

        if (paiementService.getPaiementById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        paiementService.deletePaiement(id);
        return ResponseEntity.noContent().build();
    }
}
