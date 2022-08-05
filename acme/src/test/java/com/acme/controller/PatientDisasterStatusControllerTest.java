package com.acme.controller;

import java.time.Instant;
import java.util.Date;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import com.acme.AcmeApplicationTests;
import com.acme.request.model.GetPatientDisasterStatusInput;
import com.acme.request.model.GetPatientDisasterStatusOutput;
import com.acme.request.model.PutPatientDisasterStatusInput;
import com.acme.request.model.PutPatientDisasterStatusOutput;

@Ignore
public class PatientDisasterStatusControllerTest extends AcmeApplicationTests {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PatientDisasterStatusController patientDisasterStatusController;

	@WithMockUser(username="admin",roles={"USER","ADMIN"})
	@Test
	public void testNewPatientDisasterStatus() {
		PutPatientDisasterStatusInput putPatientDisasterStatusInput = new PutPatientDisasterStatusInput();
		putPatientDisasterStatusInput.setDate(Date.from(Instant.now()));
		putPatientDisasterStatusInput.setDisasterId(4L);
		putPatientDisasterStatusInput.setFacilityNpi(1053866467L);
		putPatientDisasterStatusInput.setPatientIdFromFacility("patientidfromfacility-004");
		putPatientDisasterStatusInput.setStatusId(2);
		PutPatientDisasterStatusOutput putPatientDisasterStatusOutput = patientDisasterStatusController
				.newPatientDisasterStatus(putPatientDisasterStatusInput);
		Assert.assertNotNull(putPatientDisasterStatusOutput);
		Assert.assertNotNull(putPatientDisasterStatusOutput.getId());
	}

	@WithMockUser(username="admin",roles={"USER","ADMIN"})
	@Test
    public void testGetPatientDisasterStatus() {
    	GetPatientDisasterStatusInput getPatientDisasterStatusInput = new GetPatientDisasterStatusInput();
    	getPatientDisasterStatusInput.setFacilityNpi(1003906488L);
    	getPatientDisasterStatusInput.setPatientIdFromFacility("patientidfromfacility-001");
    	GetPatientDisasterStatusOutput getPatientDisasterStatusOutput = 
    			patientDisasterStatusController.getPatientDisasterStatus(getPatientDisasterStatusInput);
    	Assert.assertNotNull(getPatientDisasterStatusOutput);
		Assert.assertNotNull(getPatientDisasterStatusOutput.getDisasterName());
    	  
    }
}
