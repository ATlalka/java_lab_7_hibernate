package pl.edu.pwr.classfinancemanager.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.classfinancemanager.data.entities.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllBy();
    List<Payment> findAllByPersonId(Long personId);
}
