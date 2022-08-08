package com.acme.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Coforma Disaster Impact API for CMS",
        description = "API Documentation for ACME Technical Challenge - Phase 3",
        version = "1.0.0"
))
public class OpenApiConfig {

}