package com.acme.request.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new patient disaster status update.
 */
@Data
public class PutPatientDisasterStatusInput {

	/** The facility npi. */
	private Long facilityNpi;
	
	/** The patient id from facility. */
	private String patientIdFromFacility;
	
	/** The disaster id. */
	private Long disasterId;
	
	/** The date. */
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date date;
	
	/** The status id. */
	private Integer statusId;
	
}
