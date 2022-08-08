package com.acme.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;

import com.acme.AcmeApplicationTests;
import com.acme.common.AppUser;
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
		//putPatientDisasterStatusInput.setDate(Instant.now().);
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
    	
    	List<GrantedAuthority> roles = buildUserAuthority(new String[] { "USER", "ADMIN" });
    	AppUser appuser = new AppUser("user", "test", roles, 1000L);
    	
    	UsernamePasswordAuthenticationToken authentication 
    	= new UsernamePasswordAuthenticationToken(appuser, null, roles);
    	GetPatientDisasterStatusOutput getPatientDisasterStatusOutput = 
    			patientDisasterStatusController.getPatientDisasterStatus(getPatientDisasterStatusInput, authentication);
    	Assert.assertNotNull(getPatientDisasterStatusOutput);
		Assert.assertNotNull(getPatientDisasterStatusOutput.getDisasterName());
    	  
    }
	
	/**
	 * Builds the user authority.
	 *
	 * @param userRoles the user roles
	 * @return the list
	 */
	private List<GrantedAuthority> buildUserAuthority(String[] userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (String userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole));
		}

		return new ArrayList<GrantedAuthority>(setAuths);
	}
}
