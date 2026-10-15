
package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Paiement;

import java.util.List;
import java.util.Optional;

public interface PaiementService {

    List<Paiement> getAllPaiements();

    Optional<Paiement> getPaiementById(Long id);

    Paiement savePaiement(Paiement paiement);

    void deletePaiement(Long id);
}
