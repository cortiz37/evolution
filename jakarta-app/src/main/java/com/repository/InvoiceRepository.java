package com.repository;

import com.model.Invoice;

import javax.enterprise.context.ApplicationScoped;
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
        client1Invoices.add(new Invoice("i001", "c1", "Supermarket 1", 100, 100, new Date()));
        client1Invoices.add(new Invoice("i002", "c1", "Supermarket 2", 100, 90, new Date()));
        client1Invoices.add(new Invoice("i003", "c1", "Supermarket 1", 100, 100, new Date()));

        database.put("c1", client1Invoices);


        List<Invoice> client2Invoices = new ArrayList<>();
        client2Invoices.add(new Invoice("i004", "c2", "Supermarket 2", 85, 85, new Date()));

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