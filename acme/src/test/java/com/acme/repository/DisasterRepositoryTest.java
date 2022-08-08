package com.acme.repository;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.acme.AcmeApplicationTests;
import com.acme.model.Disaster;

// TODO: Auto-generated Javadoc
/**
 * The Class DisasterRepositoryTest.
 */
@TestMethodOrder(OrderAnnotation.class)
public class DisasterRepositoryTest extends AcmeApplicationTests {

	/** The disaster repository. */
	@Autowired
	DisasterRepository disasterRepository;

	/** The disaster id. */
	static Long disasterId = null;

	/**
	 * Test save.
	 */
	
	@Test
	@Order(1)
	public void testSave() {

		Disaster disaster = new Disaster();

		disaster.setName("Disaster In making");
		disaster.setStartDate(LocalDateTime.now().minusMonths(10));
		disaster.setEndDate(LocalDateTime.now().minusMinutes(2));

		disaster = disasterRepository.save(disaster);
		Assert.assertNotNull(disaster);
		Assert.assertNotNull(disaster.getId());
		disasterId = disaster.getId();

	}

	/**
	 * Test get.
	 */
	
	@Test
	@Order(2)
	public void testGet() {
		Assert.assertNotNull(disasterId);
		Disaster disaster = disasterRepository.findById(disasterId).orElse(null);
		Assert.assertNotNull(disaster);
		Assert.assertEquals("Disaster In making", disaster.getName());

	}

	/**
	 * Test delete.
	 */
	
	@Test
	@Order(3)
	public void testDelete() {
		disasterRepository.deleteById(disasterId);
		disasterId = null;
	}
	
	@AfterAll
	@BeforeAll
	public static void common() {
		disasterId = null;
		
	}
}
