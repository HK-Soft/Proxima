package com.proximar.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proximar.core.model.User;
import com.proximar.services.userServices.UserServices;

/**
 * 
 * Handle user retrieval from the database
 *
 */
@Service
public class IUserDetailsService implements UserDetailsService {

	@Autowired
	private UserServices userServices;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user;
		if ((user = userServices.getByUsername(username)) != null)
			return new IUserDetails(user);

		System.out.println("username not found.." + username);

		throw new UsernameNotFoundException("username not found..." + username);
	}

}
