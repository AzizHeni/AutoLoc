
package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;
import java.util.Optional;

public interface MaintenanceService {

    List<Maintenance> getAllMaintenances();

    Optional<Maintenance> getMaintenanceById(Long id);

    Maintenance saveMaintenance(Maintenance maintenance);

    void deleteMaintenance(Long id);
}
