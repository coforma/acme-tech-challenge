package com.acme.controller;

import com.acme.common.AppUser;
import com.acme.common.RolesEnum;
import com.acme.config.GlobalExceptionHandler;
import com.acme.model.PatientDisasterStatus;
import com.acme.repository.PatientDisasterStatusRepository;
import com.acme.request.model.DisasterSummaryInput;
import com.acme.request.model.DisasterSummaryOutput;
import com.acme.service.AuthService;
import com.acme.service.PatientDisasterStatusService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@SecurityRequirement(name = "ACME_API_JWT_TOKEN")
@RequestMapping("/disaster")
public class DisasterController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PatientDisasterStatusService patientDisasterStatusService;
    @Autowired
    private AuthService authService;

    @PreAuthorize("hasAnyRole('FSA','GOVT')")
    @GetMapping("/")
    public DisasterSummaryOutput facilityDisasterSummary(
            @RequestParam("disasterId") Long disasterId,
            @RequestParam("facilityNpi") Optional<Long> facilityNpi,
            @RequestParam("stateId") Optional<Integer> stateId,
            @RequestParam("timeFrame") Optional<String> timeFrame,
            @RequestParam("statusId") Optional<String> status,
            Authentication authentication
    ) {
        logger.info("entered");
        Long facilityNpiLong = facilityNpi.orElse(null);
        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("GOVT"))) {

            if (facilityNpiLong != null) {
                authService.checkPermissions(facilityNpiLong, authentication);
            } else {
                AppUser currentUser = (AppUser)authentication.getPrincipal();
                facilityNpiLong = currentUser.getFacilityNpi();
            }
        }
        return patientDisasterStatusService.getDisasterSummary(disasterId, facilityNpiLong, stateId.orElse(null), timeFrame.orElse(null), status.orElse(null));
    }
}

