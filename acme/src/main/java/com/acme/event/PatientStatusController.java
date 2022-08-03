package com.acme.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/patientDisasterStatus")
public class PatientStatusController {

    @Autowired
    private PatientStatusRepository disasterStatusRepository;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/")
    public Collection<PatientDisaster> createPatientStatus() {
        return disasterStatusRepository.findAll();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public PatientDisaster getStatusById(@PathVariable(value = "id") Long eventId) {
        return disasterStatusRepository.findById(eventId).get();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}/list")
    public PatientDisaster getListById(@PathVariable(value = "id") Long eventId) {
        return disasterStatusRepository.findById(eventId).get();
    }

}
