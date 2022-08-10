package com.acme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
		checkPermissions(putPatientDisasterStatusInput.getFacilityNpi(), authentication);
		
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
		
		
		checkPermissions(facilityNpi, authentication);
	
		return patientDisasterStatusService.getPatientStatus(facilityNpi, patientIdFromFacility);
	}
	
	/**
	 * Check permissions.
	 *
	 * @param facilityNpi the facility npi
	 * @param authentication the authentication
	 */
	private void checkPermissions(Long facilityNpi, Authentication authentication) {
		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(RolesEnum.GOVT.name()))) {
		    return;
		}
		AppUser currentUser = (AppUser)authentication.getPrincipal();
		Long currentUserNpi = currentUser.getFacilityNpi();
		
		if((long)facilityNpi!= (long)currentUserNpi) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Current User does not have permissions on requested facility npi");
		}
	}


}
