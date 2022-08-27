package com.example.hertamstruggle_backend2.model.person;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Patient {

    // TODO add other instance variables
    @Id
    private long id;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String AHV;


    public Patient(String firstName, String lastName, LocalDate birthDate, String AHV) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.AHV = AHV;
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

    public long getId() {
        return id;
    }
}
