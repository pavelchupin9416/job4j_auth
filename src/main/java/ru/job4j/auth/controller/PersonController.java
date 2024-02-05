package ru.job4j.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService persons;

    public PersonController(PersonService persons) {
        this.persons = persons;
    }

    @GetMapping("/")
    public List<Person> findAll() {
        return this.persons.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
       return this.persons.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return new ResponseEntity<Person>(
                this.persons.save(person) ? HttpStatus.CREATED : HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("/")
    public ResponseEntity<Person> update(@RequestBody Person person) {
        return new ResponseEntity<>(
                this.persons.update(person) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
}
    @DeleteMapping("/{id}")
    public ResponseEntity<Person> delete(@PathVariable int id) {
        return new ResponseEntity<>(
                this.persons.delete(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
