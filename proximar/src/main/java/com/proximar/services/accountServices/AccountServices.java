package com.proximar.services.accountServices;

import com.proximar.core.model.Account;

public interface AccountServices {

	public Account create(String username, String password);

	public Account getByUsername(String username);
	
	public long countAccount();

}
