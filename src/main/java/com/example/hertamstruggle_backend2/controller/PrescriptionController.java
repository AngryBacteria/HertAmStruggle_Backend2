package com.example.hertamstruggle_backend2.controller;

import com.example.hertamstruggle_backend2.HertAmStruggleBackend2Application;
import com.example.hertamstruggle_backend2.model.admin.Admin;
import com.example.hertamstruggle_backend2.model.person.Doctor;
import com.example.hertamstruggle_backend2.model.person.Patient;
import com.example.hertamstruggle_backend2.model.prescription.Drug;
import com.example.hertamstruggle_backend2.model.prescription.Prescription;
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

import java.time.LocalDate;
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

        //Json Objects
        Gson gson = Admin.gson;
        JsonObject prescription = gson.fromJson(newPrescription, JsonObject.class);

        //Fields
        int numberOfUses = prescription.get("numberOfUses").getAsInt();
        int zsrCode = prescription.get("zsrCode").getAsInt();
        String ahv = prescription.get("AHV").getAsString();
        LocalDate prescriptionDate = LocalDate.parse(prescription.get("prescriptionDate").getAsString());
        LocalDate expirationDate = LocalDate.parse(prescription.get("prescriptionDate").getAsString());


        if (HertAmStruggleBackend2Application.admin.getDoctorByZsr(zsrCode).isEmpty() ||
                HertAmStruggleBackend2Application.admin.getPatientByAHV(ahv).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Objects couldn't be found");
        }

        Doctor doctor = HertAmStruggleBackend2Application.admin.getDoctorByZsr(zsrCode).get();
        Patient patient = HertAmStruggleBackend2Application.admin.getPatientByAHV(ahv).get();

        JsonArray prescriptionDrugsArray = prescription.getAsJsonArray("drugPrescriptions");
        List<PrescriptionDrug> prescriptiondrugs = new ArrayList<>();
        for (JsonElement jsonElement : prescriptionDrugsArray){

            String atcCode = jsonElement.getAsJsonObject().get("atcCode").getAsString();
            if (HertAmStruggleBackend2Application.admin.getDrugByATC(atcCode).isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Objects couldn't be found");
            }
            Drug tmpDrug = HertAmStruggleBackend2Application.admin.getDrugByATC(atcCode).get();
            String text = jsonElement.getAsJsonObject().get("schedule").getAsString();
            prescriptiondrugs.add(new PrescriptionDrug(tmpDrug, text));
        }

        HertAmStruggleBackend2Application.admin.createPrescription(numberOfUses,
                doctor, patient, prescriptiondrugs, prescriptionDate, expirationDate);
    }

    @PutMapping(path = "/use/{id}")
    public void usePrescription(@Parameter(description = "Id of prescription to use") @PathVariable Integer id) {

        if (HertAmStruggleBackend2Application.admin.getPrescription(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prescription couldn't be found");
        }
        Prescription prescription = HertAmStruggleBackend2Application.admin.getPrescription(id).get();
        System.out.println(prescription);

        try {
            HertAmStruggleBackend2Application.admin.usePrescription(prescription);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
