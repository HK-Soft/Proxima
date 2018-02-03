package com.proximar.core.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "users_accounts")
public class Account {

	@Id
	@GeneratedValue
	private long id;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = true)
	private String token;

	@Column
	private boolean emailConfirmed = false;

	@Column
	private boolean enabled = true;

	@Column
	private boolean expired = false;

	@Column
	private boolean locked = false;

	@Column
	private boolean credentialsExpired = false;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_authorities", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"), uniqueConstraints = @UniqueConstraint(name = "UQ_users_authorities_account_id_authority_id_00", columnNames = {
			"account_id", "authority_id" }))
	private List<Authority> authorities;

	public Account() {
	}

	public Account(long id, String username, String password, boolean emailConfirmed, boolean enabled) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.emailConfirmed = emailConfirmed;
		this.enabled = enabled;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public boolean isEmailConfirmed() {
		return emailConfirmed;
	}

	public void setEmailConfirmed(boolean emailConfirmed) {
		this.emailConfirmed = emailConfirmed;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	@Override
	public String toString() {
		return "Account : username [" + getUsername() + "]  " + "password [" + getPassword() + "]";
	}

}
