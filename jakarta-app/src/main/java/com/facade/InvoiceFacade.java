package com.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Invoice;
import com.service.CustomerService;
import com.service.InvoiceService;
import com.service.RewardService;
import com.stream.KafkaProducer;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
public class InvoiceFacade {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Inject
    private InvoiceService invoiceService;
    @Inject
    private CustomerService customerService;
    @Inject
    private RewardService rewardService;

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
        if (rewardId != null && !rewardId.trim().isEmpty()) {
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
            Map<String, Object> data = new HashMap<>();
            data.put("to", Collections.singletonList(c.getEmail()));
            data.put("subject", "New Invoice!");
            data.put("body", "New Invoice created: " + invoice.getMerchant() + ", total: " + invoice.getTotalPaid());
            try {
                KafkaProducer.sendMessage(invoice.getId(), objectMapper.writeValueAsString(data));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return true;
        });
    }

    private void notifyNewReward(String customerId) {
        customerService.getCustomerById(customerId).map(c -> {
            Map<String, Object> data = new HashMap<>();
            data.put("to", Collections.singletonList(c.getEmail()));
            data.put("subject", "New Reward!");
            data.put("body", "You have earned a new reward for your next purchase. Enjoy it!");
            try {
                KafkaProducer.sendMessage("reward", objectMapper.writeValueAsString(data));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return true;
        });
    }
}
