package pl.edu.pwr.classfinancemanager.data.services;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import pl.edu.pwr.classfinancemanager.CustomDate;
import pl.edu.pwr.classfinancemanager.data.entities.Instalment;
import pl.edu.pwr.classfinancemanager.data.repositories.PersonRepository;
import pl.edu.pwr.classfinancemanager.data.entities.Person;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final InstalmentService installmentService;
    private static final Logger logger = LogManager.getLogger(PersonService.class);
    public List<Person> getPersons() {
        return personRepository.findAllBy();
    }

    public Person createPerson(String args[]) {
        try {
            Person.PersonBuilder builder = Person.builder();
            Person person = builder.name(args[0])
                    .surname(args[1])
                    .build();
            personRepository.saveAndFlush(person);
            return person;
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        return null;
    }

    //TODO logi


}
