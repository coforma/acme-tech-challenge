package com.acme.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    //@Autowired
    //private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Override
    public UserDetails loadUserByUsername(String username) {
    	UserBuilder userBuilder = User.withUsername(username).password(bCryptPasswordEncoder.encode("abcd1234"));
    	if(username.equals("admin"))
    		userBuilder.roles("USER","ADMIN");
    	else {
    		userBuilder.roles("USER");
    		}
    	return userBuilder.build();
    	}
    
    }