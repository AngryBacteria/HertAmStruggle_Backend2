package com.example.hertamstruggle_backend2.model.prescription;

import com.example.hertamstruggle_backend2.model.admin.Admin;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


public class Drug {

    private static final AtomicInteger counter = new AtomicInteger();
    
    private int id;
    private int approvalNumber;
    private String name;
    private String permitHolder;
    private String ATCCode;
    private String Category;

    public Drug(int approvalNumber, String name, String permitHolder, String ATCCode, String category) {
        this.approvalNumber = approvalNumber;
        this.name = name;
        this.permitHolder = permitHolder;
        this.ATCCode = ATCCode;
        this.Category = category;
        this.id = counter.getAndIncrement();
    }

    public Drug() {

    }

    public int getId() {
        return id;
    }

    public int getApprovalNumber() {
        return approvalNumber;
    }

    public String getName() {
        return name;
    }

    public String getPermitHolder() {
        return permitHolder;
    }

    public String getATCCode() {
        return ATCCode;
    }

    public String getCategory() {
        return Category;
    }

    public String toJson() {
        return Admin.gson.toJson(this);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setApprovalNumber(int approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPermitHolder(String permitHolder) {
        this.permitHolder = permitHolder;
    }

    public void setATCCode(String ATCCode) {
        this.ATCCode = ATCCode;
    }

    public void setCategory(String category) {
        Category = category;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Drug{");
        sb.append("id=").append(id);
        sb.append(", approvalNumber=").append(approvalNumber);
        sb.append(", name='").append(name).append('\'');
        sb.append(", permitHolder='").append(permitHolder).append('\'');
        sb.append(", ATCCode='").append(ATCCode).append('\'');
        sb.append(", Category='").append(Category).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drug drug = (Drug) o;
        return id == drug.id && approvalNumber == drug.approvalNumber && Objects.equals(name, drug.name) && Objects.equals(permitHolder, drug.permitHolder) && Objects.equals(ATCCode, drug.ATCCode) && Objects.equals(Category, drug.Category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, approvalNumber, name, permitHolder, ATCCode, Category);
    }
}
