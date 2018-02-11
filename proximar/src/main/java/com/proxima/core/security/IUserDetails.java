package com.proxima.core.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.proxima.core.model.User;

/**
 * 
 * User wrapper for authentication services To conform with Spring Security
 * Specification
 * 
 */
@SuppressWarnings("serial")
public class IUserDetails implements UserDetails {

    private User user;

    public IUserDetails(User user) {
	this.user = user;
    }

    public User getUseur() {
	return this.user;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
	return user.getAccount().getAuthorities();
    }

    public String getPassword() {
	return user.getAccount().getPassword();
    }

    public String getUsername() {
	return user.getAccount().getUsername();
    }

    public boolean isAccountNonExpired() {
	return !user.getAccount().isExpired();
    }

    public boolean isAccountNonLocked() {
	return !user.getAccount().isLocked();
    }

    public boolean isCredentialsNonExpired() {
	return !user.getAccount().isCredentialsExpired();
    }

    public boolean isEnabled() {
	return user.getAccount().isEnabled();
    }

}
