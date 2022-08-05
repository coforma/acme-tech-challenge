package com.acme.request.model;

import java.util.Date;
import java.util.List;

import com.acme.model.Patient;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new patient disaster status update.
 */
@Data
public class BatchPatientDisasterStatusInput {

	/** The facility npi. */
	private Long facilityNpi;
	/** The disaster id. */
	private Long disasterId;
	
	/** The date. */
	private Date date;
	
	List<Patient> patients;
	
	@Data	
	public static class Patient {
		private String patientIdFromFacility;
		private String statusId;
	}
	
}
