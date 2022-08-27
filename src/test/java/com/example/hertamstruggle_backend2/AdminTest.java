package com.example.hertamstruggle_backend2;

import com.example.hertamstruggle_backend2.model.admin.Admin;
import com.example.hertamstruggle_backend2.model.person.Doctor;
import com.example.hertamstruggle_backend2.model.person.Patient;
import com.example.hertamstruggle_backend2.model.prescription.Prescription;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class AdminTest {


    private Admin admin;


    public AdminTest() {

    }

    @BeforeEach
    void setup() {
        admin = new Admin();
        admin.init();

    }


    @Test
    void canUsePrescription() {
        long numberOfSupplies = 5;
        Prescription prescription = new Prescription(numberOfSupplies, null, null, null, null, null);

        prescription.usePrescription();
        assertEquals(4, prescription.getNumberOfSupplies());
    }


    @Test
    void lastTimeUsed() {

    }

    @Test
    void intervalTest() {
        admin.usePrescription(admin.getPrescription(0L));
        assertThrows(IllegalStateException.class, () -> admin.usePrescription(admin.getPrescription(0L)));
    }

    @Test
    void expirationTest(){

        assertAll(() -> assertDoesNotThrow(() -> admin.usePrescription(admin.getPrescription(0L))),
                () -> assertThrows(IllegalStateException.class, () -> admin.usePrescription(admin.getPrescription(2L))));

    }
}
