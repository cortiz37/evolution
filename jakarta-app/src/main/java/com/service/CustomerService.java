package com.service;

import com.model.Customer;
import com.repository.CustomerRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class CustomerService {

    @Inject
    private CustomerRepository customerRepository;

    public List<Customer> getAll() {
        return customerRepository.getDatabase();
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.getById(id);
    }

    public boolean delete(String id) {
        return customerRepository.delete(id);
    }

    public Customer create(Customer customer) {
        customer.setId(UUID.randomUUID().toString());
        return customerRepository.save(customer);
    }

    public Optional<Customer> replace(String id, Customer customer) {
        customer.setId(id);
        if (delete(id)) {
            return Optional.of(customerRepository.save(customer));
        }
        return Optional.empty();
    }
}