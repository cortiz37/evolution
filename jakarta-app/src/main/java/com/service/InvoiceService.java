package com.service;

import com.model.Invoice;
import com.repository.InvoiceRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class InvoiceService {

    @Inject
    private InvoiceRepository invoiceRepository;

    public List<Invoice> getAll() {
        return invoiceRepository.getDatabase();
    }

    public Optional<List<Invoice>> getAllByCustomerId(String customerId) {
        return invoiceRepository.getAllByCustomerId(customerId);
    }

    public Invoice create(Invoice invoice, String customerId) {
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(java.sql.Date.valueOf(LocalDate.now()));
        return invoiceRepository.save(invoice, customerId);
    }
}