package tn.esprit.autoloc.controller;

import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.AgenceService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
public class AgenceController {

    private final AgenceService agenceService;

    public AgenceController(AgenceService agenceService) {
        this.agenceService = agenceService;
    }

    @GetMapping
    public List<Agence> getAllAgences() {
        return agenceService.getAllAgences();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agence> getAgenceById(
            @PathVariable Long id) {
        return agenceService.getAgenceById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Agence createAgence(@RequestBody Agence agence) {
        return agenceService.saveAgence(agence);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agence> updateAgence(
            @PathVariable Long id,
            @RequestBody Agence updatedAgence) {

        return agenceService.getAgenceById(id)
                .map(existing -> {
                    // Replace these with your actual Agence fields.
                    existing.setNom(updatedAgence.getNom());
                    existing.setAdresse(updatedAgence.getAdresse());
                    existing.setTelephone(updatedAgence.getTelephone());

                    return ResponseEntity.ok(
                            agenceService.saveAgence(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgence(
            @PathVariable Long id) {

        if (agenceService.getAgenceById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        agenceService.deleteAgence(id);
        return ResponseEntity.noContent().build();
    }
}