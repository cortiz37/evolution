package com.ui;

import com.facade.InvoiceFacade;
import com.model.Invoice;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class InvoiceBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private InvoiceFacade invoiceFacade;

    public List<Invoice> getAll() {
        return invoiceFacade.getAll();
    }
}
