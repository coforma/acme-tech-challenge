package com.acme.repository;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.acme.AcmeApplicationTests;
import com.acme.model.Patient;

@TestMethodOrder(OrderAnnotation.class)
public class PatientRepositoryTest extends AcmeApplicationTests {

	static Long patientId = null;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	FacilityRepository facilityRepository;

	
	@Order(1)  
	@Test
	public void testSave() {
		Patient patient =  new Patient();
		patient.setPatientIdFromFacility("patient-facility-test-001");
		patient.setFacility(facilityRepository.findById(1001L).orElseGet(null));
		patient = patientRepository.save(patient);
		Assert.assertNotNull(patient);
		Assert.assertNotNull(patient.getId());
		patientId = patient.getId();
	}
	
	@Order(2)  
	@Test
	public void testGet() {
		Patient patient = patientRepository.findById(patientId).orElse(null);
		Assert.assertNotNull(patient);
		Assert.assertEquals("patient-facility-test-001", patient.getPatientIdFromFacility());
		Assert.assertNotNull(patient.getFacility());
		Assert.assertEquals(1001L, patient.getFacility().getId().longValue());
		
	}
	
	@Order(3)  
	@Test
	public void testDelete() {
		patientRepository.deleteById(patientId);
		
	}
	@AfterAll
	@BeforeAll
	public static void common() {
		patientId = null;
		
	}
}
