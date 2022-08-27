package com.example.hertamstruggle_backend2.controller;

import com.example.hertamstruggle_backend2.model.admin.Admin;
import com.example.hertamstruggle_backend2.model.prescription.Prescription;
import com.example.hertamstruggle_backend2.repositories.PrescriptionRepository;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping("/prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @GetMapping(path = "{id}")
    public Optional<Prescription> findById(@Parameter(description = "Id of prescription to get") @PathVariable Integer id) {
        try {
            return prescriptionRepository.findById(id);
        }
        catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prescription could not be found");
        }
    }
}
