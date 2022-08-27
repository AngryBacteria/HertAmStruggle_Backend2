package com.example.hertamstruggle_backend2.model.prescription;

import com.example.hertamstruggle_backend2.model.admin.Admin;

public class PrescriptionDrug {

    private Drug drug;
    private String prescriptionText;

    public PrescriptionDrug(Drug drug, String prescriptionText) {
        this.drug = drug;
        this.prescriptionText = prescriptionText;
    }

    public String toJSON() {
        return Admin.gson.toJson(this);
    }
}
