package com.example.hertamstruggle_backend2.model.prescription;

import java.io.Serializable;
import java.util.Objects;

public class DrugPrescriptionID implements Serializable {

    private int drug;
    private int prescription;

    public int getDrug() {
        return drug;
    }

    public void setDrug(int drug) {
        this.drug = drug;
    }

    public int getPrescription() {
        return prescription;
    }

    public void setPrescription(int prescription) {
        this.prescription = prescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrugPrescriptionID that = (DrugPrescriptionID) o;
        return drug == that.drug && prescription == that.prescription;
    }

    @Override
    public int hashCode() {
        return Objects.hash(drug, prescription);
    }
}
