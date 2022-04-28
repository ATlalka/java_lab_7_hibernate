package pl.edu.pwr.classfinancemanager.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.classfinancemanager.data.entities.Person;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAllBy();
}
