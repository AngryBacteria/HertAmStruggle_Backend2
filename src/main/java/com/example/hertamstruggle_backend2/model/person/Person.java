package com.example.hertamstruggle_backend2.model.person;

import com.example.hertamstruggle_backend2.model.admin.Admin;

public abstract class Person {

    protected long id;
    protected String firstName;
    protected String lastName;
    private static long idCount = 0;

    protected Person(String firstName, String lastName) {
        this.id = Person.idCount;
        idCount++;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return this.id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String toJSON() {
        return Admin.gson.toJson(this);
    }
}
