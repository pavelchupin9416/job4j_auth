package ru.job4j.auth.service;

import org.springframework.stereotype.Service;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public boolean save(Person person) {
        if (findById(person.getId()).isEmpty()) {
            personRepository.save(person);
            return true;
        }
        return false;
    }

    public boolean update(Person person) {
       /* return personRepository.findById(person.getId()).map(personRepository.save(person) return true;)*/

        if (findById(person.getId()).isPresent()) {
            personRepository.save(person);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        return findById(id).map(person -> {
            personRepository.delete(person);
            return true;
        }).orElseGet(() -> false
        );
    }

    public Optional<Person> findById(int id) {
        return personRepository.findById(id);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
