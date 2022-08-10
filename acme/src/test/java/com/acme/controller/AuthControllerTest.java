package com.acme.controller;

import com.acme.AcmeApplicationTests;
import com.acme.request.model.LoginInput;
import com.acme.request.model.LoginOutput;
import com.acme.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

@TestMethodOrder(OrderAnnotation.class)
public class AuthControllerTest extends AcmeApplicationTests {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AuthController authController;
	@Autowired
	UserService userService;

	@Test
	public void testControllerReturnsToken() {
		LoginInput loginInput = new LoginInput();
		loginInput.setUsername("userGovt");

		LoginOutput out = authController
				.login(loginInput);
		Assert.assertNotNull(out);
		Assert.assertNotNull(out.getToken());
	}
}
