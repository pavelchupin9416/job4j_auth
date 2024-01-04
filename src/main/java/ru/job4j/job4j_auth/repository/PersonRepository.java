package ru.job4j.job4j_auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.job4j_auth.domain.Person;

import java.util.List;
@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

}