package com.example.hertamstruggle_backend2.model.person;


import java.time.LocalDate;

public class Patient extends Person {

    // TODO add other instance variables
    private LocalDate birthDate;
    private String AHV;

    public Patient(String firstName, String lastName, LocalDate birthDate, String AHV) {
        super(firstName, lastName);
        this.birthDate = birthDate;
        this.AHV = AHV;
    }

    public String getAHV() {
        return AHV;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
