package com.acme.service;

import com.acme.common.AppUser;
import com.acme.common.JwtHelper;
import com.acme.common.RolesEnum;
import com.acme.model.UserAccount;
import com.acme.repository.UserAccountRepository;
import com.acme.request.model.LoginInput;
import com.acme.request.model.LoginOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
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

    /**
     * Check permissions.
     *
     * @param facilityNpi the facility npi
     * @param authentication the authentication
     */
    public void checkPermissions(Long facilityNpi, Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(RolesEnum.GOVT.name()))) {
            return;
        }
        AppUser currentUser = (AppUser)authentication.getPrincipal();
        Long currentUserNpi = currentUser.getFacilityNpi();

        if((long)facilityNpi!= (long)currentUserNpi) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Current User does not have permissions on requested facility npi");
        }
    }
}
