package com.model;

import java.util.Date;

public class Invoice {

    private String id;
    private String clientId;
    private String merchant;

    private double total;
    private double totalPaid;

    private Date date;

    public Invoice() {
    }

    public Invoice(String id, String clientId, String merchant, double total, double totalPaid, Date date) {
        this.id = id;
        this.clientId = clientId;
        this.merchant = merchant;
        this.total = total;
        this.totalPaid = totalPaid;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}