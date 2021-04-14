package com.repository;

import com.model.Invoice;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * In memory 'database'
 */
@ApplicationScoped
public class InvoiceRepository {

    private final static Map<String, List<Invoice>> database = new HashMap<>();

    static {
        List<Invoice> client1Invoices = new ArrayList<>();
        client1Invoices.add(new Invoice("i001", "c1", "Supermarket 1", 100, 100, java.sql.Date.valueOf(LocalDate.of(2000, 10, 4))));
        client1Invoices.add(new Invoice("i001", "c1", "Supermarket 2", 100, 90, java.sql.Date.valueOf(LocalDate.of(2000, 11, 5))));
        client1Invoices.add(new Invoice("i001", "c1", "Supermarket 1", 100, 100, java.sql.Date.valueOf(LocalDate.of(2001, 1, 3))));

        database.put("c1", client1Invoices);


        List<Invoice> client2Invoices = new ArrayList<>();
        client2Invoices.add(new Invoice("i001", "c2", "Supermarket 2", 85, 85, java.sql.Date.valueOf(LocalDate.of(2012, 4, 7))));

        database.put("c2", client2Invoices);
    }

    public Invoice save(Invoice invoice, String customerId) {
        if (!database.containsKey(customerId)) {
            database.put(customerId, new ArrayList<>());
        }
        database.get(customerId).add(invoice);
        return invoice;
    }

    public Optional<List<Invoice>> getAllByCustomerId(String customerId) {
        if (database.containsKey(customerId)) {
            return Optional.of(database.get(customerId));
        }
        return Optional.empty();
    }

    public List<Invoice> getDatabase() {
        return database.values().stream()
            .flatMap(List::stream)
            .sorted(Comparator.comparing(Invoice::getDate).reversed())
            .collect(Collectors.toList());
    }
}