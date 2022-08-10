package com.acme.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.acme.model.UserAccount;
import com.acme.repository.interfaces.UserAccountRepository;

import io.jsonwebtoken.Claims;

// TODO: Auto-generated Javadoc
/**
 * The Class AppHeaderUserDetailsService.
 */
public class AppHeaderUserDetailsService extends AppUserDetailsService {

	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/** The user account repository. */
	@Autowired
	private UserAccountRepository userAccountRepository; 


	/** The b crypt password encoder. */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/** The jwt helper. */
	@Autowired
	private JwtHelper jwtHelper;

	/**
	 * Load user by username.
	 *
	 * @param jwt the jwt
	 * @return the user details
	 */
	@Override
	public UserDetails loadUserByUsername(String jwt) {

		Claims claims = jwtHelper.decodeJWT(jwt);
		String username = claims.get("name", String.class);
		UserAccount user = userAccountRepository.getUserByName(username);
		if (user == null) {
			logger.error(" login failed for user {}", username);
			throw new UsernameNotFoundException(username + "does not exist");
		}
		return new AppUser(username, bCryptPasswordEncoder.encode(user.getPassword()),
				buildUserAuthority(user.getRoles().split(",")), Long.parseLong(user.getFacility().getNpi()));

	}
}