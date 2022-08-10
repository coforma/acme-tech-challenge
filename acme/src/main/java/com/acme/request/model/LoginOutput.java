package com.acme.request.model;

import lombok.Data;


@Data
public class LoginOutput {
        /** A valid JWT */
    private String token;
}