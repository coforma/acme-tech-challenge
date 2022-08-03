package com.acme;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.acme.common.JwtHelper;

import io.jsonwebtoken.Claims;

public class JwtHelperTest extends AcmeApplicationTests {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	JwtHelper jwtHelper;

    @Test
    public void testCreateAndParseUser() {
    	Map<String,String> claims = new HashMap<String, String>();
    	claims.put("name", "sample");
    	String jwt = jwtHelper.createJWT("sample", "ACMEAPI", "sample", 0, claims);
    	Assert.assertNotNull("jwt null", jwt);
    	logger.info("name {} , jwt {} ", "sample" , jwt);
    	Claims claimsReturned = jwtHelper.decodeJWT(jwt);
    	String username = claimsReturned.get("name", String.class);
    	Assert.assertEquals("sample",username);
    }
    
  
    
    @Test
    public void testGenerateSecretKey() {
    	
    	    SecureRandom random = new SecureRandom();
    	    byte[] bytes = new byte[50]; // 36 bytes * 8 = 288 bits, a little bit more than
    	                                 // the 256 required bits 
    	    random.nextBytes(bytes);
    	    var encoder = Base64.getUrlEncoder().withoutPadding();
    	    String key =  encoder.encodeToString(bytes);
    	    logger.info(key);
    	    
    	    
    }
}
