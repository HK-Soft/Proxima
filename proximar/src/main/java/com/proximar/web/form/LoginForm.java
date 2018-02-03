package com.proximar.web.form;

import org.hibernate.validator.constraints.NotBlank;

public class LoginForm {

	@NotBlank
	private String password;

	@NotBlank
	private String username;

	private boolean rememberMe;

	public LoginForm() {
		this.rememberMe = false;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String toString() {
		return "Login Form : Username [" + username + "] Password [" + password + "] Remmeber me [" + rememberMe + "]";
	}

}
