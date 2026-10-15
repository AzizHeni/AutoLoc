
package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.service.ContratService;

import java.util.List;
import java.util.Optional;

@Service
public class ContratServiceImpl implements ContratService {

    private final ContratRepository contratRepository;

    public ContratServiceImpl(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    @Override
    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    @Override
    public Optional<Contrat> getContratById(Long id) {
        return contratRepository.findById(id);
    }

    @Override
    public Contrat saveContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public void deleteContrat(Long id) {
        contratRepository.deleteById(id);
    }
}
