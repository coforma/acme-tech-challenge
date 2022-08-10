package com.acme.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.acme.model.UserAccount;
import com.acme.repository.interfaces.UserAccountRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class AppUserDetailsService.
 */
@Service
public class AppUserDetailsService implements UserDetailsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** The b crypt password encoder. */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	/** The user account repository. */
	@Autowired
	private UserAccountRepository userAccountRepository; 

	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		UserAccount user = userAccountRepository.getUserByName(username);
		if(user == null) {
			logger.debug("Login failed for user {}", username);
			throw new UsernameNotFoundException(username + "does not exist");
		}
		return new AppUser(username, bCryptPasswordEncoder.encode(user.getPassword()), buildUserAuthority(user.getRoles().split(",")), Long.parseLong(user.getFacility().getNpi()));
	
	}

	/**
	 * Builds the user authority.
	 *
	 * @param userRoles the user roles
	 * @return the list
	 */
	protected List<GrantedAuthority> buildUserAuthority(String[] userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (String userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole));
			setAuths.add(new SimpleGrantedAuthority("ROLE_"+userRole));
		}

		return new ArrayList<GrantedAuthority>(setAuths);
	}
}