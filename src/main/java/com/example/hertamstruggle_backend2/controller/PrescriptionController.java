package com.example.hertamstruggle_backend2.controller;

import com.example.hertamstruggle_backend2.HertAmStruggleBackend2Application;
import com.example.hertamstruggle_backend2.model.admin.Admin;
import com.example.hertamstruggle_backend2.model.person.Doctor;
import com.example.hertamstruggle_backend2.model.person.Patient;
import com.example.hertamstruggle_backend2.model.prescription.Drug;
import com.example.hertamstruggle_backend2.model.prescription.PrescriptionDrug;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "prescription found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Drug.class))}),
            @ApiResponse(responseCode = "404", description = "prescription not found",
                    content = @Content)})

    @GetMapping(path = "id/{id}")
    public String prescription(@Parameter(description = "Id of prescription to get") @PathVariable Integer id) {

        if (HertAmStruggleBackend2Application.admin.getPrescription(id).isPresent()){
            return HertAmStruggleBackend2Application.admin.getPrescription(id).get().toJson();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prescription could not be found");
        }
    }


    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody String newPrescription) {

        System.out.println(newPrescription);
        Gson gson = Admin.gson;

        JsonObject prescription = gson.fromJson(newPrescription, JsonObject.class);
        System.out.println(prescription.get("zsrCode"));
        int zsrCode = prescription.get("zsrCode").getAsInt();
        String ahv = prescription.get("AHV").getAsString();

        //Todo empty checks
        Doctor doctor = HertAmStruggleBackend2Application.admin.getDoctorByZsr(zsrCode).get();
        System.out.println(doctor);

        Patient patient = HertAmStruggleBackend2Application.admin.getPatientByAHV(ahv).get();
        System.out.println(patient);

        JsonArray prescriptionDrugsArray = prescription.getAsJsonArray("drugPrescriptions");
        System.out.println(prescriptionDrugsArray);


        List<PrescriptionDrug> prescriptiondrugs = new ArrayList<>();
        for (JsonElement jsonElement : prescriptionDrugsArray){
            System.out.println(jsonElement.getAsJsonObject().get("atcCode"));
        }



        //prescriptiondrugs.add(HertAmStruggleBackend2Application.admin.getDrugByATC());
        //prescriptiondrugs.add(HertAmStruggleBackend2Application.admin.getDrugByATC());

        //HertAmStruggleBackend2Application.admin.createPrescription(newPrescription.getNumberOfUses(),
        //        doctor, patient, prescriptiondrugs, newPrescription.getPrescriptionDate(), newPrescription.getExpirationDate());

    }
}
