package com.ui;

import com.facade.CustomerFacade;
import com.model.Customer;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CustomerBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private CustomerFacade customerFacade;

    public List<Customer> getAll() {
        return customerFacade.getAll();
    }

    public void delete(Customer customer) {
        customerFacade.delete(customer.getId());
    }
}
