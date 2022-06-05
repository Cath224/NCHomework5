package com.nchomework.netcrackerhw.dao.impl;

import com.nchomework.netcrackerhw.dao.api.PersonDao;
import com.nchomework.netcrackerhw.model.Page;
import com.nchomework.netcrackerhw.model.Person;
import com.nchomework.netcrackerhw.parsing.HwJsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class PersonDaoImpl implements PersonDao {

    @Autowired
    private HwJsonParser jsonParser;

    public Person getById(UUID id) {
        return jsonParser.parsePersonById(id);
    }

    @Override
    public List<Person> getByIds(Set<UUID> ids) {
        return jsonParser.parsePersonByIds(ids);
    }

    @Override
    public List<Person> getByNameAndLastname(String name, String lastname) {
        return jsonParser.parsePersonByFields(name, lastname);
    }

    @Override
    public List<Person> get(Page page) {
        return jsonParser.parsePersons(page);
    }

    public Person create(Person person) {
        jsonParser.writePersons(Collections.singletonList(person));
        return getById(person.getId());
    }

    @Override
    public List<Person> createAll(List<Person> persons) {
        jsonParser.writePersons(persons);
        return getByIds(persons.stream().map(Person::getId).collect(Collectors.toSet()));
    }

    public Person update(Person person) {
        jsonParser.writePersons(Collections.singletonList(person));
        return getById(person.getId());
    }

    public void deleteById(UUID id) {
       jsonParser.deletePersonsByIds(Collections.singleton(id));
    }
}
