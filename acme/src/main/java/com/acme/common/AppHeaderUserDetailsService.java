package com.acme.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.jsonwebtoken.Claims;


public class AppHeaderUserDetailsService implements UserDetailsService {

    //@Autowired
    //private UserRepository userRepository;

//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private JwtHelper jwtHelper;
	
    @Override
    public UserDetails loadUserByUsername(String jwt) {
    	Claims claims = jwtHelper.decodeJWT(jwt);
    	String userName  = claims.get("name", String.class);
    	if(userName == null)
    		return null;
    	UserBuilder userBuilder = User.withUsername(userName).password("dummy");
    	if(userName.equals("admin"))
    		userBuilder.roles("USER","ADMIN");
    	else {
    		userBuilder.roles("USER");
    		}
    	return userBuilder.build();
    	}
    
    }