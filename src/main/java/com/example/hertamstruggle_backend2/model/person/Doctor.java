package com.example.hertamstruggle_backend2.model.person;

import java.util.concurrent.atomic.AtomicInteger;

public class Doctor {

    private final static AtomicInteger counter = new AtomicInteger();

    private int id;
    private String signature;
    private String address;
    protected String firstName;
    protected String lastName;

    public Doctor(String firstName, String lastName, String address, String signature) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.signature = signature;
        this.address = address;
        this.id = counter.getAndIncrement();
    }

    public String getSignature() {
        return signature;
    }

    public String getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Doctor{");
        sb.append("id=").append(id);
        sb.append(", signature='").append(signature).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", counter=").append(counter);
        sb.append('}');
        return sb.toString();
    }
}
