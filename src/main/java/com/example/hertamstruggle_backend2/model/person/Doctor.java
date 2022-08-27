package com.example.hertamstruggle_backend2.model.person;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Doctor {

    @Id
    private long id;

    private String signature;

    private String address;
    protected String firstName;
    protected String lastName;

    public Doctor(String firstName, String lastName, String address, String signature) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.signature = signature;
        this.address = address;
    }

    public Doctor() {
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

    public long getId() {
        return id;
    }

}
