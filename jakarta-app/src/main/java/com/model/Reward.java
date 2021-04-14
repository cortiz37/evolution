package com.model;

import java.util.Date;

public class Reward {

    private String id;
    private String clientId;
    private String name;

    private Date date;

    private double discount;

    private boolean active = true;

    public Reward() {
    }

    public Reward(String id, String clientId, String name, Date date, double discount) {
        this.id = id;
        this.clientId = clientId;
        this.name = name;
        this.date = date;
        this.discount = discount;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}