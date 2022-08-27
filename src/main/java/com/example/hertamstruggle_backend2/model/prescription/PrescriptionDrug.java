package com.example.hertamstruggle_backend2.model.prescription;

import com.example.hertamstruggle_backend2.model.admin.Admin;

import javax.persistence.*;

@Entity
public class PrescriptionDrug {

    @Id
    @ManyToOne
    @JoinColumn(name = "Drug_id")
    @JsonBackReference
    private Drug drug;
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
