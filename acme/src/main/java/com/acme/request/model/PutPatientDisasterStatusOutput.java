package com.acme.request.model;

import java.util.Date;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new patient disaster status update.
 */
@Data
public class PutPatientDisasterStatusOutput {

	/** The facility npi. */
	private Long facilityNpi;
	
	/** The patient id from facility. */
	private String patientIdFromFacility;
	
	/** The disaster id. */
	private Long disasterId;
	
	/** The date. */
	private Date date;
	
	/** The status id. */
	private Integer statusId;
	
}
