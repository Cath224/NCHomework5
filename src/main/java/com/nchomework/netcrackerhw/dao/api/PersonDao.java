package com.nchomework.netcrackerhw.dao.api;

import com.nchomework.netcrackerhw.model.Page;
import com.nchomework.netcrackerhw.model.Person;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface PersonDao {

    Person create(Person person);

    List<Person> createAll(List<Person> persons);

    Person update(Person person);

    void deleteById(UUID id);

    Person getById(UUID id);

    List<Person> getByIds(Set<UUID> ids);

    List<Person> getByNameAndLastname(String name, String lastName);

    List<Person> get(Page page);

}
