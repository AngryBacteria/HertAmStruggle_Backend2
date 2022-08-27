package com.example.hertamstruggle_backend2.model.prescription;


import com.example.hertamstruggle_backend2.model.admin.Admin;
import com.example.hertamstruggle_backend2.model.person.Doctor;
import com.example.hertamstruggle_backend2.model.person.Patient;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
public class Prescription {


    @Id
    private long id;

    private long numberOfSupplies;

    @ManyToOne
    @JoinColumn(name = "Doctor_id")
    @JsonBackReference
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "Patient_id")
    @JsonBackReference
    private Patient patient;


    // private List<PrescriptionDrug> drugs;
    private LocalDate prescriptionDate, expirationDate;
    private String code;
    transient private Duration interval;
    private LocalDate lastTimeUsed;

    public Prescription(long numberOfSupplies, Doctor doctor, Patient patient, List<PrescriptionDrug> drugs, LocalDate prescriptionDate, LocalDate expirationDate) {
        this.numberOfSupplies = numberOfSupplies;
        this.doctor = doctor;
        this.patient = patient;
        // this.drugs = drugs;
        this.prescriptionDate = prescriptionDate;
        this.expirationDate = expirationDate;
        this.interval = Duration.ofDays(30);
        this.code = generateCode();
    }

    public Prescription() {

    }

    private String generateCode() {
        return UUID.randomUUID().toString();
    }

    public void usePrescription() {

        this.numberOfSupplies = this.numberOfSupplies - 1;
        this.lastTimeUsed = LocalDate.now();

    }

    public long getId() {
        return id;
    }

    public long getNumberOfSupplies() {
        return numberOfSupplies;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    //public List<PrescriptionDrug> getDrugs() {
    //    return Collections.unmodifiableList(drugs);
    //}

    public LocalDate getPrescriptionDate() {
        return prescriptionDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getCode() {
        return code;
    }

    public Duration getInterval() {
        return interval;
    }

    public LocalDate getLastTimeUsed() {
        return lastTimeUsed;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Prescription{");
        sb.append("id=").append(id);
        sb.append(", numberOfSupplies=").append(numberOfSupplies);
        sb.append(", doctor=").append(doctor);
        sb.append(", patient=").append(patient);
        // sb.append(", drugs=").append(drugs);
        sb.append(", prescriptionDate=").append(prescriptionDate);
        sb.append(", expirationDate=").append(expirationDate);
        sb.append(", code='").append(code).append('\'');
        sb.append(", interval=").append(interval.toString());
        sb.append(", lastTimeUsed=").append(lastTimeUsed);
        sb.append('}');
        return sb.toString();
    }

    public String toJSON() {
        return Admin.gson.toJson(this);
    }
}
