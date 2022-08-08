package com.acme.common;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
public class AppUser extends User {

	/** The facility npi. */
	private Long facilityNpi;

	/**
	 * Instantiates a new app user.
	 *
	 * @param username the username
	 * @param password the password
	 * @param authorities the authorities
	 */
	public AppUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Long npi) {
		super(username, password, authorities);
		this.facilityNpi = npi;
		// TODO Auto-generated constructor stub
	}

}
