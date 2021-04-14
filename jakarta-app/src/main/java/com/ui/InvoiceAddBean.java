package com.ui;

import com.facade.InvoiceFacade;
import com.facade.RewardFacade;
import com.model.Invoice;
import com.model.Reward;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class InvoiceAddBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private InvoiceFacade invoiceFacade;

    @EJB
    private RewardFacade rewardFacade;

    private Invoice invoice;

    private String rewardId;

    public String save() {
        invoiceFacade.create(invoice, invoice.getClientId(), rewardId);
        return "/invoice/invoices?faces-redirect=true";
    }

    public List<Reward> getAvailableRewards() {
        return rewardFacade.getAllActiveByCustomerId(invoice.getClientId());
    }

    public Invoice getInvoice() {
        return invoice == null ? invoice = new Invoice() : invoice;
    }

    public String getRewardId() {
        return rewardId;
    }

    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }
}
