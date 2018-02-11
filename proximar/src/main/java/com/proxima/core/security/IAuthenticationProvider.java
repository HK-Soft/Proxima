package com.proxima.core.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * Authentication handler
 *
 */
public class IAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

	String username = (String) authentication.getPrincipal();
	String password = (String) authentication.getCredentials();

	UserDetails user = userDetailsService.loadUserByUsername(username);

	if (!passwordEncoder.matches(password, user.getPassword())) {
	    throw new BadCredentialsException("Wrong password..");
	}

	return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
	return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
	this.userDetailsService = userDetailsService;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
	this.passwordEncoder = passwordEncoder;
    }

    public UserDetailsService getUserDetailsService() {
	return userDetailsService;
    }

    public PasswordEncoder getPasswordEncoder() {
	return passwordEncoder;
    }

}
