package com.acme.request.model;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class GetPatientDisasterStatusInput.
 */
@Data
public class GetPatientDisasterStatusInput {

	/** The facility npi. */
	private Long facilityNpi;

	/** The patient id from facility. */
	private String patientIdFromFacility;
}
