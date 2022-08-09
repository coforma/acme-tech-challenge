package com.acme.service;

import com.acme.common.JwtHelper;
import com.acme.model.UserAccount;
import com.acme.repository.UserAccountRepository;
import com.acme.request.model.LoginInput;
import com.acme.request.model.LoginOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    JwtHelper jwtHelper;

    public LoginOutput Login(LoginInput loginInput) {
        // Validate Credentials
        UserAccount user = userAccountRepository.getUserByName(loginInput.getUsername());
        if (user == null || !user.getPassword().equals(loginInput.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        // Build a JWT
        LoginOutput out = new LoginOutput();
        Map<String,String> claims = new HashMap<String, String>();
        claims.put("name", "sample");
        String jwt = jwtHelper.createJWT("sample", "ACMEAPI", "sample", 900000, claims); // 15min
        out.setToken(jwt); // 15min
        return out;
    }
}
