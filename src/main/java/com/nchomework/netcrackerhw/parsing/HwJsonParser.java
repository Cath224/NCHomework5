package com.nchomework.netcrackerhw.parsing;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nchomework.netcrackerhw.model.Page;
import com.nchomework.netcrackerhw.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class HwJsonParser {

    private static final String DB_FILE_LOCATION = "classpath:db/db.json";

    private static final TypeReference<Map<UUID, Person>> TYPE = new TypeReference<>() {
    };

    @Autowired
    private ObjectMapper objectMapper;


    public Person parsePersonById(UUID id) {
        try {
            Map<UUID, Person> db = objectMapper.readValue(ResourceUtils.getFile(DB_FILE_LOCATION), TYPE);
            return db.get(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> parsePersonByIds(Set<UUID> ids) {
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        try {
            Map<UUID, Person> db = objectMapper.readValue(ResourceUtils.getFile(DB_FILE_LOCATION), TYPE);
            return ids.stream()
                    .map(db::get)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> parsePersonByFields(String name, String lastName) {
        try {
            Map<UUID, Person> db = objectMapper.readValue(ResourceUtils.getFile(DB_FILE_LOCATION), TYPE);
            return db.values().stream()
                    .filter((el) -> Objects.equals(el.getName(), name) && Objects.equals(el.getLastname(), lastName))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Person> parsePersons(Page page) {
        int limit = page.getLimit();
        if (limit == 0) {
            return Collections.emptyList();
        }
        try {
            Map<UUID, Person> db = objectMapper.readValue(ResourceUtils.getFile(DB_FILE_LOCATION), TYPE);
            return new ArrayList<>(db.values()).subList(page.getOffset(), limit == -1 ? db.values().size() : limit);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Person> writePersons(List<Person> persons) {
        if (persons.isEmpty()) {
            return Collections.emptyList();
        }
        try {
            File file = ResourceUtils.getFile(DB_FILE_LOCATION);
            Map<UUID, Person> db = objectMapper.readValue(file, TYPE);
            for (Person person : persons) {
                UUID id = person.getId();
                if (id == null) {
                    id = UUID.randomUUID();
                    person.setId(id);
                }
                db.put(id, person);
            }
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, db);
            return persons;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void deletePersonsByIds(Set<UUID> personsIds) {
        if (personsIds.isEmpty()) {
            return;
        }
        try {
            File file = ResourceUtils.getFile(DB_FILE_LOCATION);
            Map<UUID, Person> db = objectMapper.readValue(file, TYPE);
            for (UUID personsId : personsIds) {
                db.remove(personsId);
            }
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, db);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
