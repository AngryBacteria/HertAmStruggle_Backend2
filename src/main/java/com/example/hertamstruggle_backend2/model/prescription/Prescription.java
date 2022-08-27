package com.example.hertamstruggle_backend2.model.prescription;


import com.example.hertamstruggle_backend2.model.person.Doctor;
import com.example.hertamstruggle_backend2.model.person.Patient;
import com.fasterxml.jackson.annotation.JsonBackReference;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
public class Prescription {


    @Id
    private int id;

    private int numbeOfUses;

    @ManyToOne
    @JoinColumn(name = "Doctor_id")
    @JsonBackReference
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "Patient_id")
    @JsonBackReference
    private Patient patient;


    @OneToMany(mappedBy = "prescription")
    private List<PrescriptionDrug> drugs;

    private LocalDate prescriptionDate, expirationDate, lastUse;
    private String code;

    public Prescription(int numbeOfUses, Doctor doctor, Patient patient, List<PrescriptionDrug> drugs, LocalDate prescriptionDate, LocalDate expirationDate) {
        this.numbeOfUses = numbeOfUses;
        this.doctor = doctor;
        this.patient = patient;
        this.drugs = drugs;
        this.prescriptionDate = prescriptionDate;
        this.expirationDate = expirationDate;
        this.code = generateCode();
    }

    private String generateCode() {
        return UUID.randomUUID().toString();
    }

    public void usePrescription() {

        this.numbeOfUses = this.numbeOfUses - 1;
        this.lastUse = LocalDate.now();

    }

    public int getId() {
        return id;
    }

    public int getNumbeOfUses() {
        return numbeOfUses;
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
        sb.append(", numberOfSupplies=").append(numbeOfUses);
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
}
