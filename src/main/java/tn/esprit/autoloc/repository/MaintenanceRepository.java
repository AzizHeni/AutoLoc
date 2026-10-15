
package tn.esprit.autoloc.repository;

import com.sun.tools.javac.Main;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.domain.Maintenance;

public interface MaintenanceRepository
        extends JpaRepository<Maintenance, Long> {
}
