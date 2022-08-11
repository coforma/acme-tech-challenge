package com.acme.controller;

import com.acme.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.acme.common.AppUser;
import com.acme.common.RolesEnum;
import com.acme.request.model.GetPatientDisasterStatusOutput;
import com.acme.request.model.PutPatientDisasterStatusInput;
import com.acme.request.model.PutPatientDisasterStatusOutput;
import com.acme.service.PatientDisasterStatusService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

// TODO: Auto-generated Javadoc
/**
 * The Class PatientDisasterStatusController.
 */
@SecurityRequirement(name = "ACME_API_JWT_TOKEN")
@RestController
@RequestMapping("/patientStatus/")
public class PatientDisasterStatusController {


	@Autowired
	PatientDisasterStatusService patientDisasterStatusService;
	@Autowired
	AuthService authService;

	/**
	 * New patient disaster status.
	 *
	 * @param putPatientDisasterStatusInput the put patient disaster status input
	 * @return the put patient disaster status output
	 */
	@PreAuthorize("hasRole('EHR')")
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public PutPatientDisasterStatusOutput newPatientDisasterStatus(
			PutPatientDisasterStatusInput putPatientDisasterStatusInput, Authentication authentication) {
		
		if(putPatientDisasterStatusInput.getFacilityNpi() == null ) 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "FacilityNpi cant be null");
		if (putPatientDisasterStatusInput.getPatientIdFromFacility() == null )
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid PatientIdFromFacility " + putPatientDisasterStatusInput.getPatientIdFromFacility() );
		if (putPatientDisasterStatusInput.getDisasterId() == null )
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid DisasterId " + putPatientDisasterStatusInput.getDisasterId() );
		if (putPatientDisasterStatusInput.getStatusId() == null )
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid PatientStatus" + putPatientDisasterStatusInput.getStatusId() );
		if (putPatientDisasterStatusInput.getDate() == null )
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Date " + putPatientDisasterStatusInput.getDate() );
		authService.checkPermissions(putPatientDisasterStatusInput.getFacilityNpi(), authentication);
		
		return patientDisasterStatusService.newPatientDisasterStatus(putPatientDisasterStatusInput);
	}

	
	/**
	 * Gets the patient disaster status.
	 *
	 * @param getPatientDisasterStatusInput the get patient disaster status input
	 * @return the patient disaster status
	 */
	@PreAuthorize("hasAnyRole('EHR','GOVT')")
	@GetMapping("/")
	public GetPatientDisasterStatusOutput getPatientDisasterStatus(@RequestParam(required=true) Long facilityNpi , @RequestParam(required=true) String patientIdFromFacility 
			, Authentication authentication) {
		
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("EHR"))) {
			checkPermissions(facilityNpi, authentication);
		}
		return patientDisasterStatusService.getPatientStatus(facilityNpi, patientIdFromFacility);
	}


}
