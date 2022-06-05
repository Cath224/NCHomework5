package com.nchomework.netcrackerhw.controller;

import com.nchomework.netcrackerhw.model.Page;
import com.nchomework.netcrackerhw.model.Person;
import com.nchomework.netcrackerhw.parsing.UUIDParser;
import com.nchomework.netcrackerhw.service.api.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("api/v1/persons")
public class PersonController {

    @Autowired
    private PersonService personService;


    @PostMapping
    public Person create(@RequestBody @Valid Person person) {
        return personService.create(person);
    }


    @PutMapping("{id}")
    public Person update(@PathVariable String id, @RequestBody @Valid Person person) {
        UUID parsedId = UUIDParser.parseUuidWithError(id);
        person.setId(parsedId);
        return personService.update(person);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable UUID id) {
        personService.deleteById(id);
    }

    @GetMapping("{id}")
    public Person getById(@PathVariable String id) {
        return personService.getById(UUIDParser.parseUuidWithError(id));
    }

    @GetMapping
    public List<Person> get(@RequestParam(required = false, defaultValue = "0") int offset,
                            @RequestParam(required = false, defaultValue = "-1") int limit) {
        return personService.get(new Page(offset, limit));
    }

    @GetMapping("params")
    public List<Person> getByNameLastname(@RequestParam String name, @RequestParam String lastname) {
        return personService.getByFirstNameAndLastName(name, lastname);
    }


}
