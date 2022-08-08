package com.acme.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.acme.AcmeApplicationTests;
import com.acme.model.User;

public class UserRepositoryTest extends AcmeApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	public void testGet() {
		User user = userRepository.getUserByName("userTx");
		Assert.assertNotNull(user);
		
		Assert.assertEquals("userTx", user.getName());
		Assert.assertNotNull(user.getFacility());
		
	}
}
