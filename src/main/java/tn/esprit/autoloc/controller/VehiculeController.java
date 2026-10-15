
package tn.esprit.autoloc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.VehiculeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {

    private final VehiculeService vehiculeService;

    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    // READ ALL
    @GetMapping
    public List<Vehicule> getAllVehicules() {
        return vehiculeService.getAllVehicules();
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getVehiculeById(
            @PathVariable Long id) {

        return vehiculeService.getVehiculeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public Vehicule createVehicule(
            @RequestBody Vehicule vehicule) {

        return vehiculeService.saveVehicule(vehicule);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> updateVehicule(
            @PathVariable Long id,
            @RequestBody Vehicule vehicule) {

        return vehiculeService.getVehiculeById(id)
                .map(existing -> {
                    vehicule.setIdVehicule(id);
                    return ResponseEntity.ok(
                            vehiculeService.saveVehicule(vehicule));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
        @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(
            @PathVariable Long id) {

        if (vehiculeService.getVehiculeById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        vehiculeService.deleteVehicule(id);
        return ResponseEntity.noContent().build();
    }
}
