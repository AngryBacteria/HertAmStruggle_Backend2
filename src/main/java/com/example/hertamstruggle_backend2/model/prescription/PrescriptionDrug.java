package com.example.hertamstruggle_backend2.model.prescription;

import com.example.hertamstruggle_backend2.model.admin.Admin;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@IdClass(DrugPrescriptionID.class)
public class PrescriptionDrug {

    @Id
    @ManyToOne
    @JoinColumn(name = "drug_id", referencedColumnName = "id")
    private Drug drug;

    @Id
    @ManyToOne
    @JoinColumn(name = "prescription_id", referencedColumnName = "id")
    private Prescription prescription;

    private String prescriptionText;

    public PrescriptionDrug(Drug drug, String prescriptionText) {
        this.drug = drug;
        this.prescriptionText = prescriptionText;
    }

    public PrescriptionDrug() {

    }
}
