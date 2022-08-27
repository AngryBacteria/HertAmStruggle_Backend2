package com.example.hertamstruggle_backend2.model.person;


public class Doctor extends Person {

    private String signature;
    private String address;

    public Doctor(String firstName, String lastName, String address, String signature) {
        super(firstName, lastName);
        this.signature = signature;
        this.address = address;
    }

    public String getSignature() {
        return signature;
    }

    public String getAddress() {
        return address;
    }

}
