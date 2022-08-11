package com.acme.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.acme.AcmeApplicationTests;
import com.acme.model.Disaster;
import com.acme.model.Patient;
import com.acme.model.PatientDisasterStatus;
import com.acme.model.PatientStatus;


@TestMethodOrder(OrderAnnotation.class)
public class PatientDisasterStatusRepositoryTest extends AcmeApplicationTests {

	static Long patientDisasterStatusId = null;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	DisasterRepository disasterRepository;
	
	@Autowired
	PatientStatusRepository patientStatusRepository;
	
	@Autowired
	PatientDisasterStatusRepository patientDisasterStatusRepository;

	@Autowired
	FacilityRepository facilityRepository;
	
	
	@Order(1)  
	@Test
	public void testSave() {
		
		PatientStatus patientStatus = patientStatusRepository.findById(101L).orElse(null);
		Disaster disaster = disasterRepository.findById(1001L).orElse(null);
		
		Patient patient =  new Patient();
		patient.setPatientIdFromFacility("patient-facility-test-001");
		patient.setFacility(facilityRepository.findById(1001L).orElseGet(null));
		patient = patientRepository.save(patient);
		Assert.assertNotNull(patient);
		
		PatientDisasterStatus patientDisasterStatus =  new PatientDisasterStatus();
		
		patientDisasterStatus.setDate(LocalDateTime.now());
		
		patientDisasterStatus.setDisaster(disaster);
		patientDisasterStatus.setPatient(patient);
		patientDisasterStatus.setPatientStatus(patientStatus);
		
		patientDisasterStatus = patientDisasterStatusRepository.save(patientDisasterStatus);
		patientDisasterStatusRepository.flush();
		Assert.assertNotNull(patientDisasterStatus);
		Assert.assertNotNull(patientDisasterStatus.getId());
		Assert.assertNotNull(patientDisasterStatus.getDisaster());
		Assert.assertNotNull(patientDisasterStatus.getPatient());
		Assert.assertNotNull(patientDisasterStatus.getPatientStatus());
		Assert.assertNotNull(patientDisasterStatus.getDate());
		
		patientDisasterStatusId = patientDisasterStatus.getId();
	}
	
	@Order(2)
	@Test
	public void testGet() {
		List<PatientDisasterStatus> patientDisasterStatusList = patientDisasterStatusRepository.findAll();
		Assert.assertNotNull(patientDisasterStatusList);
		Assert.assertFalse("empty", patientDisasterStatusList.isEmpty());
		PatientDisasterStatus patientDisasterStatus = patientDisasterStatusList.get(0);
		Assert.assertNotNull(patientDisasterStatus.getId());
		Assert.assertNotNull("patient null", patientDisasterStatus.getPatient());
		Assert.assertNotNull("disaster null", patientDisasterStatus.getDisaster());
		Assert.assertNotNull("patientStatus null", patientDisasterStatus.getPatientStatus());
		
	}

	
	@Order(3)
	@Test
	public void testGetLatestByFacilityIdAndPatientIdInFacility() {
		List<PatientDisasterStatus> patientDisasterStatusList=
				patientDisasterStatusRepository.findLatestByFacilityAndPatientFacilityId("1003906488", "patient-facility-test-001");
		Assert.assertNotNull(patientDisasterStatusList);
		Assert.assertFalse("empty", patientDisasterStatusList.isEmpty());
		PatientDisasterStatus patientDisasterStatus = patientDisasterStatusList.get(0);
		Assert.assertNotNull(patientDisasterStatus);
		Assert.assertNotNull(patientDisasterStatus.getId());
		Assert.assertEquals("1003906488", patientDisasterStatus.getPatient().getFacility().getNpi());
		Assert.assertEquals("UPMC Northwest", patientDisasterStatus.getPatient().getFacility().getFacilityName());
		Assert.assertEquals(101L, patientDisasterStatus.getPatientStatus().getId().longValue());
		Assert.assertEquals(1001L, patientDisasterStatus.getDisaster().getId().longValue());
		Assert.assertTrue(patientDisasterStatus.getDate().isAfter(LocalDateTime.now().minusHours(1)));
		
	}
	
	@Order(4)
	@Test
	public void testDelete() {
		PatientDisasterStatus patientDisasterStatus = patientDisasterStatusRepository.findById(patientDisasterStatusId).orElse(null);
		Assert.assertNotNull(patientDisasterStatus);
		Assert.assertNotNull(patientDisasterStatus.getPatient());
		patientDisasterStatusRepository.deleteById(patientDisasterStatusId);
		patientRepository.delete(patientDisasterStatus.getPatient());
	}
	
	@AfterAll
	@BeforeAll
	public static void common() {
		patientDisasterStatusId = null;
		
	}
}
