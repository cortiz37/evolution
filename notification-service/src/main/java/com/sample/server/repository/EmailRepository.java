package com.sample.server.repository;

import com.sample.server.model.Email;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * In memory 'database'
 */
@Repository
public class EmailRepository {

    private final static List<Email> database = new ArrayList<>();

    public Email save(Email email) {
        database.add(email);
        return email;
    }

    public boolean delete(String id) {
        return database.stream()
            .filter(e -> e.getId().equals(id))
            .findFirst()
            .map(database::remove)
            .orElse(false);
    }

    public List<Email> getAll() {
        return database.stream()
            .sorted(Comparator.comparing(Email::getDate).reversed())
            .collect(Collectors.toList());
    }
}