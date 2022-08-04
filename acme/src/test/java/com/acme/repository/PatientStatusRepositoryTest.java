package com.acme.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.acme.AcmeApplicationTests;
import com.acme.model.PatientStatus;

public class PatientStatusRepositoryTest extends AcmeApplicationTests {

	@Autowired
	PatientStatusRepository patientStatusRepository;

	@Ignore
	@Test
	public void testSave() {
		PatientStatus patientStatus = new PatientStatus();
		patientStatus.setId(100L);
		patientStatus.setStatus("READY");
		patientStatus = patientStatusRepository.save(patientStatus);
		Assert.assertNotNull(patientStatus);
		Assert.assertNotNull(patientStatus.getId());
		
	}
	
	@Test
	public void testGet() {
		List<PatientStatus> patientStatuses = patientStatusRepository.findAll();
		Assert.assertNotNull(patientStatuses);
		Assert.assertFalse("empty", patientStatuses.isEmpty());
		Assert.assertEquals(1L, patientStatuses.get(0).getId().longValue());
		Assert.assertEquals("Unaffected", patientStatuses.get(0).getStatus());
		
	}
}
