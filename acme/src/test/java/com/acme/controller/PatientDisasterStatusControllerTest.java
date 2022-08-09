package com.acme.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;

import com.acme.AcmeApplicationTests;
import com.acme.common.AppUser;
import com.acme.model.PatientDisasterStatus;
import com.acme.repository.PatientDisasterStatusRepository;
import com.acme.repository.PatientRepository;
import com.acme.request.model.GetPatientDisasterStatusInput;
import com.acme.request.model.GetPatientDisasterStatusOutput;
import com.acme.request.model.PutPatientDisasterStatusInput;
import com.acme.request.model.PutPatientDisasterStatusOutput;

@TestMethodOrder(OrderAnnotation.class)
public class PatientDisasterStatusControllerTest extends AcmeApplicationTests {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PatientDisasterStatusController patientDisasterStatusController;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	PatientDisasterStatusRepository patientDisasterStatusRepository;

	@Order(1)
	@WithMockUser(username="admin",roles={"GOVT","EHR"})
	@Test
	public void testNewPatientDisasterStatus() {
		PutPatientDisasterStatusInput putPatientDisasterStatusInput = new PutPatientDisasterStatusInput();
		putPatientDisasterStatusInput.setDate(LocalDateTime.now());
		putPatientDisasterStatusInput.setDisasterId(1001L);
		putPatientDisasterStatusInput.setFacilityNpi(1003906488L);
		putPatientDisasterStatusInput.setPatientIdFromFacility("patientidfromfacility-test-001");
		putPatientDisasterStatusInput.setStatusId(101);
		
	 	List<GrantedAuthority> roles = buildUserAuthority(new String[] { "EHR", "GOVT" });
    	AppUser appuser = new AppUser("user", "test", roles, 1003906488L);
    	
    	UsernamePasswordAuthenticationToken authentication 
    	= new UsernamePasswordAuthenticationToken(appuser, null, roles);
    	
		PutPatientDisasterStatusOutput putPatientDisasterStatusOutput = patientDisasterStatusController
				.newPatientDisasterStatus(putPatientDisasterStatusInput, authentication);
		Assert.assertNotNull(putPatientDisasterStatusOutput);
		Assert.assertNotNull(putPatientDisasterStatusOutput.getId());
	}

	@Order(2)
	@WithMockUser(username="admin",roles={"EHR", "GOVT"})
	@Test
    public void testGetPatientDisasterStatus() {
    	GetPatientDisasterStatusInput getPatientDisasterStatusInput = new GetPatientDisasterStatusInput();
    	getPatientDisasterStatusInput.setFacilityNpi(1003906488L);
    	getPatientDisasterStatusInput.setPatientIdFromFacility("patientidfromfacility-test-001");
    	
    	List<GrantedAuthority> roles = buildUserAuthority(new String[] {"EHR", "GOVT" });
    	AppUser appuser = new AppUser("user", "test", roles, 1003906488L);
    	
    	UsernamePasswordAuthenticationToken authentication 
    	= new UsernamePasswordAuthenticationToken(appuser, null, roles);
    	GetPatientDisasterStatusOutput getPatientDisasterStatusOutput = 
    			patientDisasterStatusController.getPatientDisasterStatus(1003906488L, "patientidfromfacility-test-001", authentication);
    	Assert.assertNotNull(getPatientDisasterStatusOutput);
		Assert.assertNotNull(getPatientDisasterStatusOutput.getDisasterName());
    	  
    }
	
	@Order(3)
	@Test
	public void testDelete() {
		List<PatientDisasterStatus> patientDisasterStatusList = patientDisasterStatusRepository.findLatestByFacilityAndPatientFacilityId("1003906488", "patientidfromfacility-test-001", Pageable.ofSize(1));
		Assert.assertNotNull(patientDisasterStatusList);
		Assert.assertNotNull(patientDisasterStatusList.get(0).getPatient());
		patientDisasterStatusRepository.deleteById(patientDisasterStatusList.get(0).getId());
		patientRepository.delete(patientDisasterStatusList.get(0).getPatient());
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
