package com.example.hertamstruggle_backend2.controller;

import com.example.hertamstruggle_backend2.HertAmStruggleBackend2Application;
import com.example.hertamstruggle_backend2.model.admin.Admin;
import com.example.hertamstruggle_backend2.model.person.Doctor;
import com.example.hertamstruggle_backend2.model.prescription.Drug;
import com.example.hertamstruggle_backend2.model.prescription.Prescription;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    //Doctor
    @GetMapping(path = "doctor/hin/{hin}")
    public Doctor doctor(@Parameter(description = "HIN-Adress of Doctor to get") @PathVariable String hin) {

        if (HertAmStruggleBackend2Application.admin.getDoctorByHin(hin).isPresent()){
            return HertAmStruggleBackend2Application.admin.getDoctorByHin(hin).get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor could not be found");
        }
    }

    @GetMapping(path = "doctor/zsr/{zsr}")
    public Doctor doctor(@Parameter(description = "HIN-Adress of Doctor to get") @PathVariable int zsr) {

        if (HertAmStruggleBackend2Application.admin.getDoctorByZsr(zsr).isPresent()){
            return HertAmStruggleBackend2Application.admin.getDoctorByZsr(zsr).get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor could not be found");
        }
    }

    @GetMapping(path = "doctor/all")
    public String getAllPrescriptions() {
        List<Doctor> doctors = HertAmStruggleBackend2Application.admin.getDoctors();
        return Admin.gson.toJson(doctors);
    }

}
