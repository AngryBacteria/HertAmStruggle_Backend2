package com.example.hertamstruggle_backend2.model.admin;

import com.example.hertamstruggle_backend2.model.person.Doctor;
import com.example.hertamstruggle_backend2.model.person.Patient;
import com.example.hertamstruggle_backend2.model.prescription.Drug;
import com.example.hertamstruggle_backend2.model.prescription.Prescription;
import com.example.hertamstruggle_backend2.model.prescription.PrescriptionDrug;
import com.google.gson.Gson;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Admin {

    private Map<Integer, Doctor> doctors;
    private Map<Integer, Patient> patients;
    private Map<Integer, Drug> drugs;
    private Map<Integer, Prescription> prescriptions;
    public static Gson gson = CustomizedGson.getInstance();

    public Admin() {
        this.doctors = new HashMap<>();
        this.patients = new HashMap<>();
        this.drugs = new HashMap<>();
        this.prescriptions = new HashMap<>();
        init();
    }

    public static void main(String[] args) {
        Admin admin = new Admin();

        System.out.println(admin.getPrescription(0).get().toJson());

    }

    //Create methods
    public Prescription createPrescription(int numberOfSupplies, Doctor doctor, Patient patient, List<PrescriptionDrug> prescriptionDrugs, LocalDate prescriptionDate, LocalDate expirationDate) {
        Prescription prescription = new Prescription(numberOfSupplies, doctor, patient, prescriptionDrugs, prescriptionDate, expirationDate);
        this.addObjectToHashMap(prescription);
        return prescription;
    }

    public Patient createPatient(String firstName, String lastName, LocalDate birthDate, String AHV) {
        Patient patient = new Patient(firstName, lastName, birthDate, AHV);
        this.addObjectToHashMap(patient);
        return patient;
    }

    public Doctor createDoctor(String firstName, String lastName, String signature, String address) {
        Doctor doctor = new Doctor(firstName, lastName, signature, address);
        this.addObjectToHashMap(doctor);
        return doctor;
    }

    public void usePrescription(Prescription prescription) {

        if (prescription.getNumberOfUses() == 1) {
            prescription.usePrescription();
            this.prescriptions.remove(prescription.getId());
            return;
        }

        if (prescription.getLastTimeUsed() != null) {
            if (prescription.getLastTimeUsed().plusDays(30).isAfter(LocalDate.now())) {
                throw new IllegalStateException("This prescription cannot be used, last time it was used is too short ago");
            }
        }

        if (prescription.getExpirationDate().isBefore(LocalDate.now())) {
            throw new IllegalStateException("Prescription is expired");
        } else {
            prescription.usePrescription();
        }
    }


    //Query Methods
    public Optional<Prescription> getPrescription(int id) {
        return Optional.ofNullable(prescriptions.get(id));
    }

    public Optional<Drug> getDrug(int id) {
        return Optional.ofNullable(drugs.get(id));
    }

    public Optional<Doctor> getDoctorByHin(String hinAddress){
        //doctors.values().stream().forEach();
        return null;
    }

    public void init() {


        Doctor doctor1 = new Doctor("Hans", "Wurst", "Schanzenstrasse 5, 2500 Biel", "123456789");
        Doctor doctor2 = new Doctor("Petra", "Sturzenegger", "Evergreenterrace 1, 6512 Springfield", "987654321");

        this.addObjectToHashMap(doctor1);
        this.addObjectToHashMap(doctor2);

        Patient patient1 = new Patient("Simon", "Walzer", LocalDate.now().minusYears(30), "756.5678.1234.58");
        Patient patient2 = new Patient("Melina", "Kracher", LocalDate.now().minusYears(25), "756.5555.1111.58");

        this.addObjectToHashMap(patient1);
        this.addObjectToHashMap(patient2);

        Drug drug1 = new Drug(123456, "Dafalgan", "Pfizer", "JSONDRULO", "C");
        Drug drug2 = new Drug(538156, "Paracetamol", "Pfizer", "R0LFG45EN2ER", "A");

        this.addObjectToHashMap(drug1);
        this.addObjectToHashMap(drug2);

        PrescriptionDrug presDrug1 = new PrescriptionDrug(drug1, "Morgens, Mittags");
        PrescriptionDrug presDrug2 = new PrescriptionDrug(drug2, "Morgens, Mittags");
        PrescriptionDrug presDrug3 = new PrescriptionDrug(drug1, "Dreimal täglich");

        List<PrescriptionDrug> presDrugList1 = new ArrayList<PrescriptionDrug>();
        presDrugList1.add(presDrug1);
        presDrugList1.add(presDrug2);
        presDrugList1.add(presDrug3);

        List<PrescriptionDrug> presDrugList2 = new ArrayList<PrescriptionDrug>();
        presDrugList2.add(presDrug1);

        Prescription prescription1 = new Prescription(10, doctor1, patient1, presDrugList1, LocalDate.now(), LocalDate.now().plusMonths(3));
        Prescription prescription2 = new Prescription(6, doctor2, patient2, presDrugList2, LocalDate.now(), LocalDate.now().plusMonths(6));
        Prescription prescriptionExpired = new Prescription(6, doctor2, patient2, presDrugList2, LocalDate.now(), LocalDate.now().minusMonths(6));


        this.addObjectToHashMap(prescription1);
        this.addObjectToHashMap(prescription2);
        this.addObjectToHashMap(prescriptionExpired);

    }

    //Adding methods
    public <T> void addObjectToHashMap(T object) {
        if (object instanceof Doctor) {
            addDoctor((Doctor) object);
        }
        if (object instanceof Patient) {
            addPatient((Patient) object);
        }
        if (object instanceof Prescription) {
            addPrescription((Prescription) object);
        }
        if (object instanceof Drug) {
            addDrug((Drug) object);
        }
    }

    private void addDoctor(Doctor doctor) {
        checkIfObjectExists(doctors, doctor.getId());
        doctors.put(doctor.getId(), doctor);
    }

    private void addPatient(Patient patient) {
        checkIfObjectExists(patients, patient.getId());
        patients.put(patient.getId(), patient);
    }

    private void addDrug(Drug drug) {
        checkIfObjectExists(drugs, drug.getId());
        drugs.put(drug.getId(), drug);
    }

    private void addPrescription(Prescription prescription) {
        checkIfObjectExists(prescriptions, prescription.getId());
        prescriptions.put(prescription.getId(), prescription);
    }

    private <T> void checkIfObjectExists(Map<Integer, T> hashMap, int id) {
        if (hashMap.containsKey(id)) {
            throw new RuntimeException("This id is already used.");
        }
    }
}
