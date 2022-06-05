package com.nchomework.netcrackerhw.service.Impl;

import com.nchomework.netcrackerhw.dao.api.PersonDao;
import com.nchomework.netcrackerhw.model.Page;
import com.nchomework.netcrackerhw.model.Person;
import com.nchomework.netcrackerhw.service.api.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public Person create(Person person) {
        return personDao.create(person);
    }

    @Override
    public List<Person> createAll(List<Person> persons) {
        return personDao.createAll(persons);
    }

    @Override
    public Person update(Person person) {
        return personDao.update(person);
    }

    @Override
    public void deleteById(UUID id) {
        personDao.deleteById(id);
    }

    @Override
    public Person getById(UUID id) {
        return personDao.getById(id);
    }

    @Override
    public List<Person> get(Page page) {
        return personDao.get(page);
    }

    @Override
    public List<Person> getByFirstNameAndLastName(String firstName, String lastName) {
        return personDao.getByNameAndLastname(firstName, lastName);
    }
}
