package com.acme.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acme.model.PatientDisasterStatus;
import com.acme.repository.PatientDisasterStatusRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "ACME_API_JWT_TOKEN")
@RestController
@RequestMapping("/patientStatusByDisaster/")
public class DisasterStatusController {

    @Autowired
    private PatientDisasterStatusRepository disasterStatusRepository;

    @PreAuthorize("hasAnyRole('EHR','GOVT','FSA')")
    @GetMapping("/id")
    public Collection<PatientDisasterStatus> byIdForDateMonthYear(@RequestParam(value = "date") String dateMMYY) {
        return disasterStatusRepository.findAll();
    }
    
    @PreAuthorize("hasAnyRole('EHR','GOVT','FSA')")
    @GetMapping("/{disasterId}/list/")
    public Collection<PatientDisasterStatus> listByDisasterId(@PathVariable(value = "disasterId") String disasterId) {
        return disasterStatusRepository.findAll();
    }

    @PreAuthorize("hasAnyRole('EHR','GOVT','FSA')")
    @GetMapping("/{disasterId}/patentList/")
    public Collection<PatientDisasterStatus> patientListDisasterId(@PathVariable(value = "disasterId") String disasterId) {
        return disasterStatusRepository.findAll();
    }

}
