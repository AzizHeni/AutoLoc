
package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Equipement;

import java.util.List;
import java.util.Optional;

public interface EquipementService {

    List<Equipement> getAllEquipements();

    Optional<Equipement> getEquipementById(Long id);

    Equipement saveEquipement(Equipement equipement);

    void deleteEquipement(Long id);
}
