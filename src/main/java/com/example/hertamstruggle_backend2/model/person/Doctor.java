package com.example.hertamstruggle_backend2.model.person;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Doctor {

    private final static AtomicInteger counter = new AtomicInteger();

    private int id;
    private String hinEmailAddress;
    private String address;
    private String firstName;
    private String lastName;
    private int zsrCode;


    public Doctor(String firstName, String lastName, String hinEmailAddress, String address, int zsrCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = counter.getAndIncrement();
        this.hinEmailAddress = hinEmailAddress;
        this.address = address;
        this.zsrCode = zsrCode;
    }

    public String getHinEmailAddress() {
        return hinEmailAddress;
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

    public int getZsrCode() {
        return zsrCode;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Doctor{");
        sb.append("id=").append(id);
        sb.append(", signature='").append(hinEmailAddress).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", zsr=").append(zsrCode);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id == doctor.id && zsrCode == doctor.zsrCode && Objects.equals(hinEmailAddress, doctor.hinEmailAddress) && Objects.equals(address, doctor.address) && Objects.equals(firstName, doctor.firstName) && Objects.equals(lastName, doctor.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hinEmailAddress, address, firstName, lastName, zsrCode);
    }
}
