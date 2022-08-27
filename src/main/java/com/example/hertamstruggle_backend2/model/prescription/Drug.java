package com.example.hertamstruggle_backend2.model.prescription;

import com.example.hertamstruggle_backend2.model.admin.Admin;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Drug {

    @Id
    private long id;

    private long approvalNumber;

    private String drugName;

    private String permitHolder;

    private String ATCcode;

    private String category;

    public Drug(long approvalNumber, String drugName, String permitHolder, String ATCcode, String category) {
        this.approvalNumber = approvalNumber;
        this.drugName = drugName;
        this.permitHolder = permitHolder;
        this.ATCcode = ATCcode;
        this.category = category;
    }

    public Drug() {

    }

    public long getId() {
        return id;
    }

    public long getApprovalNumber() {
        return approvalNumber;
    }

    public String getDrugName() {
        return drugName;
    }

    public String getPermitHolder() {
        return permitHolder;
    }

    public String getATCcode() {
        return ATCcode;
    }

    public String getCategory() {
        return category;
    }

    public String toJSON() {
        return Admin.gson.toJson(this);
    }


}
