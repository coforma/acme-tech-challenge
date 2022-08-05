package com.acme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.acme.request.model.GetPatientDisasterStatusInput;
import com.acme.request.model.GetPatientDisasterStatusOutput;
import com.acme.request.model.PutPatientDisasterStatusInput;
import com.acme.request.model.PutPatientDisasterStatusOutput;

import ch.qos.logback.core.status.Status;

// TODO: Auto-generated Javadoc
/**
 * The Class PatientDisasterStatusController.
 */
@RestController
@RequestMapping("/patientStatus/")
public class PatientDisasterStatusController {

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

	@Autowired
	private PatientStatusRepository patientStatusRepository;

	
	/**
	 * New patient disaster status.
	 *
	 * @param putPatientDisasterStatusInput the put patient disaster status input
	 * @return the put patient disaster status output
	 */
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/")
	public PutPatientDisasterStatusOutput newPatientDisasterStatus(
			PutPatientDisasterStatusInput putPatientDisasterStatusInput) {

		Disaster disaster = disasterRepository.findById(putPatientDisasterStatusInput.getDisasterId().longValue())
				.orElse(null);
		if (disaster == null)
			return null;

		PatientStatus patientStatus = patientStatusRepository
				.findById(putPatientDisasterStatusInput.getStatusId().longValue()).orElse(null);
		if (patientStatus == null)
			return null;
		Patient patient = getOrCreatePatient(putPatientDisasterStatusInput);
		if (patient == null)
			return null;

		PatientDisasterStatus patientDisasterStatus = new PatientDisasterStatus();
		patientDisasterStatus.setDate(putPatientDisasterStatusInput.getDate());
		patientDisasterStatus.setDisaster(disaster);
		patientDisasterStatus.setPatientStatus(patientStatus);
		patientDisasterStatus.setPatient(patient);

		patientDisasterStatus = disasterStatusRepository.saveAndFlush(patientDisasterStatus);
		if (patientDisasterStatus == null || patientDisasterStatus.getId() == null)
			return null;
		PutPatientDisasterStatusOutput putPatientDisasterStatusOutput = new PutPatientDisasterStatusOutput();
		putPatientDisasterStatusOutput.setId(patientDisasterStatus.getId());
		return putPatientDisasterStatusOutput;
	}

	
	/**
	 * Gets the patient disaster status.
	 *
	 * @param getPatientDisasterStatusInput the get patient disaster status input
	 * @return the patient disaster status
	 */
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/get")
	public GetPatientDisasterStatusOutput getPatientDisasterStatus(
			GetPatientDisasterStatusInput getPatientDisasterStatusInput) {
		GetPatientDisasterStatusOutput response = null;
		List<PatientDisasterStatus> returnedStatusList = disasterStatusRepository
				.findLatestByFacilityAndPatientFacilityId(getPatientDisasterStatusInput.getFacilityNpi().toString(),
						getPatientDisasterStatusInput.getPatientIdFromFacility(), Pageable.ofSize(1));
		if (returnedStatusList != null && !returnedStatusList.isEmpty()) {
			PatientDisasterStatus pds = returnedStatusList.get(0);
			response = new GetPatientDisasterStatusOutput();
			response.setDate(pds.getDate());
			response.setDisasterName(pds.getDisaster().getName());
			if (pds.getPatient() != null) {
				if (pds.getPatient().getFacility() != null) {
					response.setFacilityLocation(pds.getPatient().getFacility().getFacilityName());
				}
				if (pds.getPatient().getId() != null) {
					response.setPatientId(pds.getPatient().getId().toString());
				}
			}
			if (pds.getPatientStatus() != null)
				response.setPatientStatus(pds.getPatientStatus().getStatus());

		}
		return response;
	}

	/**
	 * List by patient id.
	 *
	 * @param patientId the patient id
	 * @return the patient disaster status
	 */
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/{patientId}/list")
	public PatientDisasterStatus listByPatientId(@PathVariable(value = "patientId") Long patientId) {
		// TODO
		return null;
	}

	/**
	 * Gets the status by id.
	 *
	 * @param dateMMYY the date MMYY
	 * @return the status by id
	 */
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/{id}")
	public PatientDisasterStatus getStatusById(@RequestParam(value = "date") String dateMMYY) {
		// TODO
		return null;
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
			return null;
		patient = new Patient();
		patient.setFacility(flist.get(0));
		patient.setPatientIdFromFacility(putPatientDisasterStatusInput.getPatientIdFromFacility());
		patient = patientRepository.saveAndFlush(patient);
		if(patient == null || patient.getId() == null)
			return null;
		return patient;

	}

}
