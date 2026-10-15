package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;

import java.util.List;
import java.util.Optional;

public interface EmployeService {

    List<Employe> getAllEmployes();

    Optional<Employe> getEmployeById(Long id);

    Employe saveEmploye(Employe employe);

    void deleteEmploye(Long id);
}