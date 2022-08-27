package com.example.hertamstruggle_backend2.controller;

import com.example.hertamstruggle_backend2.model.prescription.Prescription;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;


@RestController
@RequestMapping("/prescription")
public class PrescriptionController {

    @GetMapping(path = "{id}")
    public Iterable<Prescription> findById(@Parameter(description = "Id of prescription to get") @PathVariable long id) {
        try {
            return null;
        }
        catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prescription could not be found");
        }
    }


}
