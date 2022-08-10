package com.acme.service;

import com.acme.common.JwtHelper;
import com.acme.model.UserAccount;
import com.acme.repository.UserAccountRepository;
import com.acme.request.model.LoginInput;
import com.acme.request.model.LoginOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    JwtHelper jwtHelper;
    public LoginOutput login(LoginInput loginInput) {
        String username = loginInput.getUsername();
        UserAccount user = userAccountRepository.getUserByName(username);


        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        // Build a JWT
        LoginOutput out = new LoginOutput();
        Map<String,String> claims = new HashMap<String, String>();
        claims.put("name", username);
        String jwt = jwtHelper.createJWT(username, "ACMEAPI", username, 900000, claims); // 15min
        out.setToken(jwt); // 15min
        return out;
    }
}
