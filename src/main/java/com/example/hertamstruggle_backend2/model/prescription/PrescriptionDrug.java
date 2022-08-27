package com.example.hertamstruggle_backend2.model.prescription;

import com.example.hertamstruggle_backend2.model.admin.Admin;

import java.util.concurrent.atomic.AtomicInteger;


public class PrescriptionDrug {

    private static final AtomicInteger counter = new AtomicInteger();

    private int id;
    private Drug drug;
    private Prescription prescription;
    private String prescriptionText;

    public PrescriptionDrug(Drug drug, String prescriptionText) {
        this.drug = drug;
        this.prescriptionText = prescriptionText;
        this.id = counter.getAndIncrement();
    }

    public PrescriptionDrug() {

    }


    public String toJson() {
        return Admin.gson.toJson(this);
    }
}
