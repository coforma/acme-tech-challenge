package com.acme.request.model;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new patient disaster status update.
 */

/**
 * Instantiates a new put patient disaster status output.
 */
@Data
@Builder
public class GetPatientDisasterStatusOutput {

	/** The date. */
	private LocalDateTime date;

	/** The patient id. */
	private String patientId;

	/** The disaster name. */
	private String disasterName;

	/** The patient status. */
	private String patientStatus;

	/** The facility location. */
	private String facilityLocation;

}
