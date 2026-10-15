
package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;
import java.util.Optional;

public interface VehiculeService {

    List<Vehicule> getAllVehicules();

    Optional<Vehicule> getVehiculeById(Long id);

    Vehicule saveVehicule(Vehicule vehicule);

    void deleteVehicule(Long id);
}
