package com.acme.repository;

import com.acme.repository.interfaces.PatientStatusRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.acme.AcmeApplicationTests;
import com.acme.model.PatientStatus;

public class PatientStatusRepositoryTest extends AcmeApplicationTests {

	@Autowired
    PatientStatusRepository patientStatusRepository;

	
	@Test
	public void testGet() {
		PatientStatus patientStatus = patientStatusRepository.findById(101L).orElse(null);
		Assert.assertNotNull(patientStatus);
		
		Assert.assertEquals(101L, patientStatus.getId().longValue());
		Assert.assertEquals("unaffected", patientStatus.getStatus());
		
	}
}
