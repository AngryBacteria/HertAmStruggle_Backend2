package com.example.hertamstruggle_backend2.repositories;

import com.example.hertamstruggle_backend2.model.prescription.Prescription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PrescriptionRepository extends CrudRepository<Prescription, Integer> {



}
