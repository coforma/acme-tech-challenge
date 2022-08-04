package com.acme.repository;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.acme.AcmeApplicationTests;
import com.acme.model.Disaster;
import com.acme.request.model.BatchPatientDisasterStatusInput;

public class DisasterRepositoryTest extends AcmeApplicationTests {

	@Autowired
	DisasterRepository disasterRepository;

	@Ignore
	@Test
	public void testSave() {
	  
		Disaster disaster = new Disaster();
		disaster.setId(100L);
		disaster.setName("Disaster In making");
		disaster.setStartDate(Date.from(java.time.ZonedDateTime.now().minusMonths(2).toInstant()));
		disaster.setEndDate(Date.from(java.time.ZonedDateTime.now().toInstant()));
		
		//disaster = disasterRepository.save(disaster);
		Assert.assertNotNull(disaster);
		Assert.assertNotNull(disaster.getId());
		
		
	}

	@Ignore
	@Test
	public void testGet() {
		List<Disaster> disasters = disasterRepository.findAll();
		Assert.assertNotNull(disasters);
		Assert.assertFalse("empty", disasters.isEmpty());
		Assert.assertEquals(100L, disasters.get(0).getId().longValue());
		Assert.assertEquals("Disaster In making", disasters.get(0).getName());
		Assert.assertEquals(1659640475000L, disasters.get(0).getEndDate().getTime());
		Assert.assertEquals(1654370075000L, disasters.get(0).getStartDate().getTime());
		
	}
}
