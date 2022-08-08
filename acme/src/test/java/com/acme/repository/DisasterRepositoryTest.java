package com.acme.repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.acme.AcmeApplicationTests;
import com.acme.model.Disaster;

public class DisasterRepositoryTest extends AcmeApplicationTests {

	@Autowired
	DisasterRepository disasterRepository;

	@Ignore
	@Test
	public void testSave() {
	  
		Disaster disaster = new Disaster();
		disaster.setId(100L);
		disaster.setName("Disaster In making");
		disaster.setStartDate(LocalDateTime.now().minusMonths(10));
		disaster.setEndDate(LocalDateTime.now().minusMinutes(2));
		
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
		Assert.assertEquals(1659640475000L, disasters.get(0).getEndDate().atZone(ZoneId.systemDefault()).toEpochSecond());
		Assert.assertEquals(1654370075000L, disasters.get(0).getStartDate().atZone(ZoneId.systemDefault()).toEpochSecond());
		
	}
}
