package com.model;

import java.util.Date;

public class Customer {

    private String id;
    private String name;
    private String address;
    private String email;

    private Date date;

    private boolean active;

    public Customer() {
    }

    public Customer(String id, String name, String address, String email, Date date, boolean active) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.date = date;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}