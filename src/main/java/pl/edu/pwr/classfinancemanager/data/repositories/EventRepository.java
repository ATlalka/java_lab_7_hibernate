package pl.edu.pwr.classfinancemanager.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.classfinancemanager.data.entities.Event;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllBy();
    Optional<Event> findById(Long id);
}
