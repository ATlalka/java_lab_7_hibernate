package pl.edu.pwr.classfinancemanager.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.classfinancemanager.data.entities.Instalment;
import pl.edu.pwr.classfinancemanager.data.entities.Payment;

import java.util.List;


public interface InstalmentRepository extends JpaRepository<Instalment, Long> {
    List<Instalment> findAllBy();
}
