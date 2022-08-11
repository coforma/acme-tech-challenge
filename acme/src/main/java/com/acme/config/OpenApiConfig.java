package com.acme.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Coforma Disaster Impact API for CMS",
        description = "API Documentation for ACME Technical Challenge - Phase 3" +
				"\n\nTo authorize: generate a JWT token using the auth endpoint below.",
        version = "1.0.0"
))
@SecurityScheme(
	    name = "ACME_API_JWT_TOKEN",
	    type = SecuritySchemeType.APIKEY,
	    in= SecuritySchemeIn.HEADER
	    
	)
public class OpenApiConfig {

}