package com.proxima.core.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.proxima.core.model.Account;
import com.proxima.web.validator.annotation.IsUnique;
import com.proxima.web.validator.annotation.Matches;

@Matches(fields = { "password", "passwordConfirmation" })
public class AccountDTO {

	@NotBlank
	@Size(min = 4, max = 14)
	@IsUnique(columnName = "username", entityClass = Account.class)
	private String username;

	@NotBlank
	@Size(min = 8, max = 21)
	private String password;

	@NotBlank
	private String passwordConfirmation;

	public AccountDTO() {
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

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	@Override
	public String toString() {
		return "Registraino Form : Username [" + username + "] Passaword [" + password + "] Password confirmation ["
				+ passwordConfirmation + "]";
	}

}
