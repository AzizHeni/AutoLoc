
package tn.esprit.autoloc.controller;

import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.EquipementService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
public class EquipementController {

    private final EquipementService equipementService;

    public EquipementController(EquipementService equipementService) {
        this.equipementService = equipementService;
    }

    // READ ALL
    @GetMapping
    public List<Equipement> getAllEquipements() {
        return equipementService.getAllEquipements();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Equipement> getEquipementById(
            @PathVariable Long id) {

        return equipementService.getEquipementById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public Equipement createEquipement(
            @RequestBody Equipement equipement) {

        return equipementService.saveEquipement(equipement);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Equipement> updateEquipement(
            @PathVariable Long id,
            @RequestBody Equipement updatedEquipement) {

        return equipementService.getEquipementById(id)
                .map(existing -> {
                    existing.setLibelle(updatedEquipement.getLibelle());

                    return ResponseEntity.ok(
                            equipementService.saveEquipement(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipement(
            @PathVariable Long id) {

        if (equipementService.getEquipementById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        equipementService.deleteEquipement(id);
        return ResponseEntity.noContent().build();
    }
}
