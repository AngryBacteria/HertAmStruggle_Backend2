package com.example.hertamstruggle_backend2.controller;


import com.example.hertamstruggle_backend2.HertAmStruggleBackend2Application;
import com.example.hertamstruggle_backend2.model.admin.Admin;
import com.example.hertamstruggle_backend2.model.person.Doctor;
import com.example.hertamstruggle_backend2.model.prescription.Drug;
import com.example.hertamstruggle_backend2.model.prescription.Prescription;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/drug")
public class DrugController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drug found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Drug.class))}),
            @ApiResponse(responseCode = "404", description = "Drug not found",
                    content = @Content)})
    @GetMapping(path = "id/{id}")
    public Drug drug(@Parameter(description = "Id of Drug to get") @PathVariable Integer id) {

        if (HertAmStruggleBackend2Application.admin.getDrug(id).isPresent()){
            return HertAmStruggleBackend2Application.admin.getDrug(id).get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drug could not be found");
        }
    }

    @GetMapping(path = "atc/{atc}")
    public Drug drug(@Parameter(description = "ATC-Code of Drug to get") @PathVariable String atc) {

        if (HertAmStruggleBackend2Application.admin.getDrugByATC(atc).isPresent()){
            return HertAmStruggleBackend2Application.admin.getDrugByATC(atc).get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drug could not be found");
        }
    }

    @GetMapping(path = "doctor/all")
    public String getAllDrugs() {
        List<Drug> drugs = HertAmStruggleBackend2Application.admin.getDrugs();
        return Admin.gson.toJson(drugs);
    }
}
