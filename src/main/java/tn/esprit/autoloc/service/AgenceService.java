
package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;
import java.util.Optional;

public interface AgenceService {

    List<Agence> getAllAgences();

    Optional<Agence> getAgenceById(Long id);

    Agence saveAgence(Agence agence);

    void deleteAgence(Long id);
}
