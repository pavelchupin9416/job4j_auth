package ru.job4j.auth.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class PersonService implements UserDetailsService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Boolean> save(Person person) {
        if (findById(person.getId()).isEmpty()) {
            personRepository.save(person);
            return Optional.of(true);
        }
        return Optional.of(false);
    }

    public boolean update(Person person) {
      return personRepository.findById(person.getId()).map(oldPerson -> {
          personRepository.save(person);
          return true;
      }).orElseGet(() -> false
      );
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = personRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getLogin(), user.getPassword(), emptyList());
    }
}
