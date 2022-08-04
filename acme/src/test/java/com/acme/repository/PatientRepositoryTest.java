package com.acme.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.acme.AcmeApplicationTests;
import com.acme.model.Facility;
import com.acme.model.Patient;
import com.acme.model.StateCode;

@TestMethodOrder(OrderAnnotation.class)
public class PatientRepositoryTest extends AcmeApplicationTests {

	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	FacilityRepository facilityRepository;

	
	//@Order(1)  
	@Ignore
	@Test
	public void testSave() {
		Patient patient =  new Patient();
		patient.setId(1L);
		patient.setPatientIdFromFacility("patient-facility-001");
		
		
		patient.setFacility(facilityRepository.findById(1L).orElseGet(null));
		
		patient = patientRepository.save(patient);
		Assert.assertNotNull(patient);
		Assert.assertNotNull(patient.getId());
		
	}
	
	@Order(2)  
	@Test
	public void testGet() {
		List<Patient> patients = patientRepository.findAll();
		Assert.assertNotNull(patients);
		Assert.assertFalse("empty", patients.isEmpty());
		Assert.assertEquals(1L, patients.get(0).getId().longValue());
		Assert.assertEquals("patient-facility-001", patients.get(0).getPatientIdFromFacility());
		
		
		Assert.assertNotNull(patients.get(0).getFacility());
		Facility facility = patients.get(0).getFacility();
		Assert.assertNotNull(facility);
		Assert.assertEquals("HeadeQaurters", facility.getFacilityName());
		Assert.assertTrue(facility.getIsOpen());
	}
}
