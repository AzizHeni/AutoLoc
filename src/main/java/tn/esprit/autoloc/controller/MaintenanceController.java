
package tn.esprit.autoloc.controller;

import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.MaintenanceService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(
            MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    // READ ALL
    @GetMapping
    public List<Maintenance> getAllMaintenances() {
        return maintenanceService.getAllMaintenances();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getMaintenanceById(
            @PathVariable Long id) {

        return maintenanceService.getMaintenanceById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public Maintenance createMaintenance(
            @RequestBody Maintenance maintenance) {

        return maintenanceService.saveMaintenance(maintenance);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> updateMaintenance(
            @PathVariable Long id,
            @RequestBody Maintenance updatedMaintenance) {

        return maintenanceService.getMaintenanceById(id)
                .map(existing -> {

                    existing.setDescription(
                            updatedMaintenance.getDescription());
                    existing.setVehicule(updatedMaintenance.getVehicule());

                    return ResponseEntity.ok(
                            maintenanceService.saveMaintenance(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenance(
            @PathVariable Long id) {

        if (maintenanceService.getMaintenanceById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}
