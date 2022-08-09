package com.acme.controller;

import com.acme.request.model.LoginInput;
import com.acme.request.model.LoginOutput;
import com.acme.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/auth/", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private UserService userService;
    /** The logger. */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "/login")
    @Operation(summary = "Login with a username and password", description = "Returns a valid JWT for a user to be used with the API")
    public LoginOutput login(@RequestBody LoginInput loginInput) {
        logger.info("Welcome");
        return userService.Login(loginInput);
    }

}
