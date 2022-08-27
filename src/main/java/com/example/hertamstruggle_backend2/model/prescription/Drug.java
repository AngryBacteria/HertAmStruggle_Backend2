package com.example.hertamstruggle_backend2.model.prescription;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Drug {

    @Id
    private int id;

    private int approvalNumber;

    private String name;

    private String permitHolder;

    private String ATCcode;

    private String category;

    @OneToMany(mappedBy = "drug")
    private List<PrescriptionDrug> prescriptions;

    public Drug(int approvalNumber, String name, String permitHolder, String ATCCode, String category) {
        this.approvalNumber = approvalNumber;
        this.name = name;
        this.permitHolder = permitHolder;
        this.ATCCode = ATCCode;
        this.Category = category;
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


}
