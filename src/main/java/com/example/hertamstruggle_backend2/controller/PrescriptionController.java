package com.example.hertamstruggle_backend2.controller;

import com.example.hertamstruggle_backend2.HertAmStruggleBackend2Application;
import com.example.hertamstruggle_backend2.model.prescription.Drug;
import com.example.hertamstruggle_backend2.model.prescription.Prescription;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "prescription found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Drug.class))}),
            @ApiResponse(responseCode = "404", description = "prescription not found",
                    content = @Content)})

    @GetMapping(path = "{id}")
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
    public void insert(@RequestBody Prescription newPrescription) {

        System.out.println(newPrescription);

    }
}
