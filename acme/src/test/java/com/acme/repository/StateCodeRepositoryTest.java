package com.acme.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.acme.AcmeApplicationTests;
import com.acme.model.StateCode;

public class StateCodeRepositoryTest extends AcmeApplicationTests {

	@Autowired
	StateCodeRepository stateCodeRepository;

	@Ignore
	@Test
	public void testSave() {
		StateCode stateCode = new StateCode();
		stateCode.setId(100L);
		stateCode.setName("ACCEPTED");
		stateCode.setCode("ACC");
		stateCode = stateCodeRepository.save(stateCode);
		Assert.assertNotNull(stateCode);
		Assert.assertNotNull(stateCode.getId());
		
	}
	
	@Test
	public void testGet() {
		List<StateCode> stateCodes = stateCodeRepository.findAll();
		Assert.assertNotNull(stateCodes);
		Assert.assertFalse("empty", stateCodes.isEmpty());
		Assert.assertEquals(1L, stateCodes.get(0).getId().longValue());
		Assert.assertEquals("ACCEPTED", stateCodes.get(0).getName());
		Assert.assertEquals("ACC", stateCodes.get(0).getCode());
	}
}
