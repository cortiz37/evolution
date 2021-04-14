package com.repository;

import com.model.Customer;
import com.service.EmailSenderService;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * In memory 'database'
 */
@ApplicationScoped
public class CustomerRepository {

    private final static List<Customer> database = new ArrayList<>();

    static {
        String default_email = EmailSenderService.email_address;

        database.add(new Customer("c1", "John", "5th ave. 2331", default_email, java.sql.Date.valueOf(LocalDate.of(2000, 1, 1)), true));
        database.add(new Customer("c2", "Mary", "Grand H S/N", default_email, java.sql.Date.valueOf(LocalDate.of(2010, 12, 10)), true));
        database.add(new Customer("c3", "Lina", "November 1st. Old Town", default_email, java.sql.Date.valueOf(LocalDate.of(2020, 10, 22)), true));
        database.add(new Customer("c4", "Marc", "Halberbunt 55", default_email, java.sql.Date.valueOf(LocalDate.of(2020, 1, 5)), true));
        database.add(new Customer("c5", "Cristie", "Central Plaza, 2nd ave.", default_email, java.sql.Date.valueOf(LocalDate.of(2020, 7, 9)), true));
        database.add(new Customer("c6", "Alfred", "Town way, 3445", default_email, java.sql.Date.valueOf(LocalDate.of(2020, 6, 8)), true));
        database.add(new Customer("c7", "Albert", "First Plaza, 66", default_email, java.sql.Date.valueOf(LocalDate.of(2020, 2, 21)), true));
        database.add(new Customer("c8", "Claudia", "Moundoule, East 11", default_email, java.sql.Date.valueOf(LocalDate.of(2005, 4, 30)), true));
    }

    public Customer save(Customer customer) {
        database.add(customer);
        return customer;
    }

    public Optional<Customer> getById(String id) {
        return database.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public boolean delete(String id) {
        return database.stream()
            .filter(e -> e.getId().equals(id))
            .findFirst()
            .map(database::remove)
            .orElse(false);
    }

    public List<Customer> getDatabase() {
        return database.stream()
            .sorted(Comparator.comparing(Customer::getId))
            .collect(Collectors.toList());
    }
}