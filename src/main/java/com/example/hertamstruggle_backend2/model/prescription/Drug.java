package com.example.hertamstruggle_backend2.model.prescription;

import com.example.hertamstruggle_backend2.model.admin.Admin;

public class Drug {


    private long id, approvalNumber;
    private String drugName, permitHolder, ATCcode, category;

    private static long idCount = 0;

    public Drug(long approvalNumber, String drugName, String permitHolder, String ATCcode, String category) {
        this.id = Drug.idCount;
        Drug.idCount++;
        this.approvalNumber = approvalNumber;
        this.drugName = drugName;
        this.permitHolder = permitHolder;
        this.ATCcode = ATCcode;
        this.category = category;
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
