package com.example.hertamstruggle_backend2.model.prescription;


import com.example.hertamstruggle_backend2.model.admin.Admin;
import com.example.hertamstruggle_backend2.model.person.Doctor;
import com.example.hertamstruggle_backend2.model.person.Patient;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Prescription {

    private static final AtomicInteger counter = new AtomicInteger();


    private int id;
    private int numberOfUses;
    private Doctor doctor;
    private Patient patient;
    private List<PrescriptionDrug> drugs;

    private LocalDate prescriptionDate, expirationDate, lastUse;
    private String code;

    public Prescription(int numberOfUses, Doctor doctor, Patient patient, List<PrescriptionDrug> drugs, LocalDate prescriptionDate, LocalDate expirationDate) {
        this.numberOfUses = numberOfUses;
        this.doctor = doctor;
        this.patient = patient;
        this.drugs = drugs;
        this.prescriptionDate = prescriptionDate;
        this.expirationDate = expirationDate;
        this.code = generateCode();
        this.id = counter.getAndIncrement();
    }

    private String generateCode() {
        return UUID.randomUUID().toString();
    }

    public void usePrescription() {

        this.numberOfUses = this.numberOfUses - 1;
        this.lastUse = LocalDate.now();

    }

    public int getId() {
        return id;
    }

    public int getNumberOfUses() {
        return numberOfUses;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public List<PrescriptionDrug> getDrugs() {
        return Collections.unmodifiableList(drugs);
    }

    public LocalDate getPrescriptionDate() {
        return prescriptionDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getCode() {
        return code;
    }

    public LocalDate getLastTimeUsed() {
        return lastUse;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Prescription{");
        sb.append("id=").append(id);
        sb.append(", numberOfSupplies=").append(numberOfUses);
        sb.append(", doctor=").append(doctor);
        sb.append(", patient=").append(patient);
        sb.append(", drugs=").append(drugs);
        sb.append(", prescriptionDate=").append(prescriptionDate);
        sb.append(", expirationDate=").append(expirationDate);
        sb.append(", code='").append(code).append('\'');
        sb.append(", lastTimeUsed=").append(lastUse);
        sb.append('}');
        return sb.toString();
    }

    public String toJson() {
        return Admin.gson.toJson(this);
    }
}
