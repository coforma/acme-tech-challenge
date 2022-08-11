package com.acme.service;

import java.util.List;

import com.acme.common.DisasterSummaryResult;
import com.acme.request.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.acme.model.Disaster;
import com.acme.model.Facility;
import com.acme.model.Patient;
import com.acme.model.PatientDisasterStatus;
import com.acme.model.PatientStatus;
import com.acme.repository.DisasterRepository;
import com.acme.repository.FacilityRepository;
import com.acme.repository.PatientDisasterStatusRepository;
import com.acme.repository.PatientRepository;
import com.acme.repository.PatientStatusRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class PatientDisasterStatusService.
 */
@Service
public class PatientDisasterStatusService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/** The disaster status repository. */
	@Autowired
	private PatientDisasterStatusRepository disasterStatusRepository;

	/** The patient repository. */
	@Autowired
	private PatientRepository patientRepository;

	/** The facility repository. */
	@Autowired
	private FacilityRepository facilityRepository;

	/** The disaster repository. */
	@Autowired
	private DisasterRepository disasterRepository;

	/** The patient status repository. */
	@Autowired
	private PatientStatusRepository patientStatusRepository;

	/**
	 * New patient disaster status.
	 *
	 * @param putPatientDisasterStatusInput the put patient disaster status input
	 * @return the put patient disaster status output
	 */
	public PutPatientDisasterStatusOutput newPatientDisasterStatus(
			PutPatientDisasterStatusInput putPatientDisasterStatusInput) {

		Disaster disaster = disasterRepository.findById(putPatientDisasterStatusInput.getDisasterId().longValue())
				.orElse(null);
		
		if (disaster == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Given disasterId " + putPatientDisasterStatusInput.getDisasterId() + " is not found in system");
		
		PatientStatus patientStatus = patientStatusRepository
				.findById(putPatientDisasterStatusInput.getStatusId().longValue()).orElse(null);
		if (patientStatus == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Invalid patient status ID  " + putPatientDisasterStatusInput.getStatusId());

		Patient patient = getOrCreatePatient(putPatientDisasterStatusInput);
		if (patient == null)
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to get or create new patient");

		PatientDisasterStatus patientDisasterStatus = new PatientDisasterStatus();
		patientDisasterStatus.setDate(putPatientDisasterStatusInput.getDate());
		patientDisasterStatus.setDisaster(disaster);
		patientDisasterStatus.setPatientStatus(patientStatus);
		patientDisasterStatus.setPatient(patient);

		patientDisasterStatus = disasterStatusRepository.saveAndFlush(patientDisasterStatus);
		if (patientDisasterStatus == null || patientDisasterStatus.getId() == null)
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Unable to get or create Patient disaster status record");
		PutPatientDisasterStatusOutput putPatientDisasterStatusOutput = new PutPatientDisasterStatusOutput();
		putPatientDisasterStatusOutput.setId(patientDisasterStatus.getId());
		return putPatientDisasterStatusOutput;
	}

	/**
	 * Gets the patient status.
	 *
	 * @param facilityNpi the facility npi
	 * @param patientIdFromFacility the patient id from facility
	 * @return the patient status
	 */
	public GetPatientDisasterStatusOutput getPatientStatus(Long facilityNpi, String patientIdFromFacility) {
		GetPatientDisasterStatusOutput response = null;
		List<PatientDisasterStatus> returnedStatusList = disasterStatusRepository
				.findLatestByFacilityAndPatientFacilityId(facilityNpi.toString(), patientIdFromFacility,
						Pageable.ofSize(1));
		if (returnedStatusList != null && !returnedStatusList.isEmpty()) {
			PatientDisasterStatus pds = returnedStatusList.get(0);
			response = new GetPatientDisasterStatusOutput();
			response.setDate(pds.getDate());
			response.setDisasterName(pds.getDisaster().getName());
			if (pds.getPatient() != null) {
				if (pds.getPatient().getFacility() != null) {
					response.setFacilityLocation(pds.getPatient().getFacility().getStateCode().getName());
				}
				if (pds.getPatient().getId() != null) {
					response.setPatientId(pds.getPatient().getId().toString());
				}
			}
			if (pds.getPatientStatus() != null)
				response.setPatientStatus(pds.getPatientStatus().getStatus());

		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No record found for search criteria");
		}
		return response;
	}

	/**
	 * Gets the or create patient.
	 *
	 * @param putPatientDisasterStatusInput the put patient disaster status input
	 * @return the or create patient
	 */
	public Patient getOrCreatePatient(PutPatientDisasterStatusInput putPatientDisasterStatusInput) {

		Patient patient = patientRepository.findByFacilityAndPatientFacilityId(
				putPatientDisasterStatusInput.getFacilityNpi().toString(),
				putPatientDisasterStatusInput.getPatientIdFromFacility());
		if (patient != null)
			return patient;
		Facility facility = new Facility();
		facility.setNpi(putPatientDisasterStatusInput.getFacilityNpi().toString());
		Example<Facility> example = Example.of(facility);
		List<Facility> flist = facilityRepository.findAll(example);
		if (flist == null || flist.isEmpty())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Invalid facilityNpi " + putPatientDisasterStatusInput.getFacilityNpi());
		patient = new Patient();
		patient.setFacility(flist.get(0));
		patient.setPatientIdFromFacility(putPatientDisasterStatusInput.getPatientIdFromFacility());
		patient = patientRepository.saveAndFlush(patient);
		if (patient == null || patient.getId() == null)
			return null;
		return patient;

	}

	public DisasterSummaryOutput getDisasterSummary(Long disasterId,
													  Long facilityNpi,
													  Integer stateId,
													  String timeFrame,
													  Integer statusId) {
		logger.info("in service" + " - " + disasterId + " - " + facilityNpi);

		List<DisasterSummaryResult> results = disasterStatusRepository.findDisasterSummary(disasterId,
				facilityNpi,
				timeFrame,
				stateId,
				statusId);
		DisasterSummaryOutput out = new DisasterSummaryOutput();

		for (DisasterSummaryResult r: results) {
			switch (r.getStatus()) {
				case "unaffected":
					out.setUnaffected(r.getTotal());
					break;
				case "injured":
					out.setInjured(r.getTotal());
					break;
				case "ill in facility":
					out.setIllInFacility(r.getTotal());
					break;
				case "ill not in facility":
					out.setIllNotInFacility(r.getTotal());
					break;
				case "deceased":
					out.setDeceased(r.getTotal());
					break;
				case "isolated":
					out.setIsolated(r.getTotal());
					break;
			}
		} 

		return out;
	}
}
