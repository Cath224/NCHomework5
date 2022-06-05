package com.nchomework.netcrackerhw.service.api;

import com.nchomework.netcrackerhw.model.Page;
import com.nchomework.netcrackerhw.model.Person;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    Person create(Person person);

    List<Person> createAll(List<Person> persons);

    Person update(Person person);

    void deleteById(UUID id);

    Person getById(UUID id);

    List<Person> get(Page page);

    List<Person> getByFirstNameAndLastName(String firstName, String lastName);

}
