package com.example.hertamstruggle_backend2.model.person;


import com.example.hertamstruggle_backend2.model.admin.Admin;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;


public class Patient {

    private static final AtomicInteger counter = new AtomicInteger();

    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String AHV;



    public Patient(String firstName, String lastName, LocalDate birthDate, String AHV) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.AHV = AHV;
        this.id = counter.getAndIncrement();
    }

    public Patient() {

    }

    public String getAHV() {
        return AHV;
    }

    public LocalDate getBirthDate() {
        return birthDate;
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

    public String toJson() {
        return Admin.gson.toJson(this);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Patient{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", AHV='").append(AHV).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
