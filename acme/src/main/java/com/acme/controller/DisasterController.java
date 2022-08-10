package com.acme.controller;

import com.acme.common.AppUser;
import com.acme.common.RolesEnum;
import com.acme.model.PatientDisasterStatus;
import com.acme.repository.PatientDisasterStatusRepository;
import com.acme.request.model.DisasterSummaryInput;
import com.acme.request.model.DisasterSummaryOutput;
import com.acme.service.AuthService;
import com.acme.service.PatientDisasterStatusService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@RestController
@SecurityRequirement(name = "ACME_API_JWT_TOKEN")
@RequestMapping("/disaster/")
public class DisasterController {

    @Autowired
    private PatientDisasterStatusService patientDisasterStatusService;
    @Autowired
    private AuthService authService;

    @PreAuthorize("hasAnyRole('FSA','GOVT')")
    @GetMapping("/")
    public List<PatientDisasterStatus> facilityDisasterSummary(@RequestBody DisasterSummaryInput disasterSummaryInput, Authentication authentication) {

//    public DisasterSummaryOutput facilityDisasterSummary(@RequestBody DisasterSummaryInput disasterSummaryInput, Authentication authentication) {
        if (!authentication.getAuthorities().contains("GOVT")) {
            if (disasterSummaryInput.getFacilityNpi() != null) {
                authService.checkPermissions(disasterSummaryInput.getFacilityNpi(), authentication);
            } else {
                // TODO: pass user's facility ID
            }
        }
        return patientDisasterStatusService.getDisasterSummary(disasterSummaryInput);
    }
}

