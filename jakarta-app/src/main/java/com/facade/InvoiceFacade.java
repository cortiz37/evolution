package com.facade;

import com.model.Email;
import com.model.Invoice;
import com.service.CustomerService;
import com.service.InvoiceService;
import com.service.RewardService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
public class InvoiceFacade {

    @Inject
    private InvoiceService invoiceService;

    @Inject
    private CustomerService customerService;

    @Inject
    private RewardService rewardService;

    @EJB
    private EmailFacade emailFacade;

    public List<Invoice> getAll() {
        return invoiceService.getAll();
    }

    public Optional<List<Invoice>> getAllByCustomerId(String customerId) {
        return invoiceService.getAllByCustomerId(customerId);
    }

    public Invoice create(Invoice invoice, String customerId, String rewardId) {
        invoice.setTotalPaid(invoice.getTotal());

        boolean rewarded = applyReward(rewardId, invoice, customerId);

        Invoice created = invoiceService.create(invoice, customerId);

        notifyNewInvoice(customerId, invoice);

        if (!rewarded) {
            if (rewardService.registerAmount(customerId, created.getTotalPaid())) {
                notifyNewReward(customerId);
            }
        }

        return created;
    }

    private double calculateDiscount(double total, double discount) {
        double result = total * (100 - discount) / 100;
        return Math.round(result * 100.0) / 100.0;
    }

    private boolean applyReward(String rewardId, Invoice invoice, String customerId) {
        boolean rewarded = false;
        if (rewardId != null) {
            rewarded = rewardService.getAllActiveByCustomerId(customerId)
                .stream()
                .filter(r -> r.getId().equals(rewardId))
                .findFirst().map(r -> {
                    invoice.setTotalPaid(calculateDiscount(invoice.getTotal(), r.getDiscount()));
                    r.setActive(false);
                    return true;
                }).orElse(false);
        }
        return rewarded;
    }

    private void notifyNewInvoice(String customerId, Invoice invoice) {
        customerService.getCustomerById(customerId).map(c -> {
            Email email = new Email(
                c.getEmail(),
                "New Invoice!",
                "New Invoice created: " + invoice.getMerchant() + ", total: " + invoice.getTotalPaid()
            );
            emailFacade.create(email);
            return true;
        });
    }

    private void notifyNewReward(String customerId) {
        customerService.getCustomerById(customerId).map(c -> {
            Email email = new Email(
                c.getEmail(),
                "New Reward!",
                "You have earned a new reward for your next purchase. Enjoy it!"
            );
            emailFacade.create(email);
            return true;
        });
    }
}
