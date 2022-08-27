package com.example.hertamstruggle_backend2.model.prescription;

import com.example.hertamstruggle_backend2.model.admin.Admin;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class PrescriptionDrug {

    @Id
    @ManyToOne
    @JoinColumn(name = "Drug_id")
    @JsonBackReference
    private Drug drug;

    @Id
    @ManyToOne
    @JoinColumn(name = "Drug_id")
    @JsonBackReference
    private Prescription prescription;

    private String prescriptionText;

    public PrescriptionDrug(Drug drug, String prescriptionText) {
        this.drug = drug;
        this.prescriptionText = prescriptionText;
    }

    public PrescriptionDrug() {

    }

    public String toJSON() {
        return Admin.gson.toJson(this);
    }
}
