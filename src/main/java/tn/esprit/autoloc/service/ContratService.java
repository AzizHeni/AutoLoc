
package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;

import java.util.List;
import java.util.Optional;

public interface ContratService {

    List<Contrat> getAllContrats();

    Optional<Contrat> getContratById(Long id);

    Contrat saveContrat(Contrat contrat);

    void deleteContrat(Long id);
}
