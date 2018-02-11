package com.proxima.services.accountServices;

import org.springframework.data.repository.CrudRepository;

import com.proxima.core.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

	public Account findByUsername(String username);

}
