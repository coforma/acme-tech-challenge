package com.acme.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.model.PatientDisasterStatus;
import com.acme.repository.PatientDisasterStatusRepository;

@RestController
@RequestMapping("/patientDisasterStatus")
public class PatientStatusController {

    @Autowired
    private PatientDisasterStatusRepository disasterStatusRepository;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/")
    public Collection<PatientDisasterStatus> createPatientStatus() {
        return disasterStatusRepository.findAll();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public PatientDisasterStatus getStatusById(@PathVariable(value = "id") Long eventId) {
        return disasterStatusRepository.findById(eventId).get();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}/list")
    public PatientDisasterStatus getListById(@PathVariable(value = "id") Long eventId) {
        return disasterStatusRepository.findById(eventId).get();
    }

}
