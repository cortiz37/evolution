package com.facade;

import com.model.Customer;
import com.service.CustomerService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
public class CustomerFacade {

    @Inject
    private CustomerService customerService;

    public List<Customer> getAll() {
        return customerService.getAll();
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerService.getCustomerById(id);
    }

    public boolean delete(String id) {
        return customerService.delete(id);
    }

    public Customer create(Customer customer) {
        return customerService.create(customer);
    }

    public Optional<Customer> replace(String id, Customer customer) {
        return customerService.replace(id, customer);
    }
}
