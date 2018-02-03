package com.proximar.services.accountServices;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proximar.ProximarUtils;
import com.proximar.core.model.Account;

@Service
public class IAccountServices implements AccountServices {

	@Autowired
	private AccountRepository accountRepository;

	@Transactional
	public Account create(String username, String password) {
		Account account = new Account(0, username, password, false, true);
		account.setToken(ProximarUtils.generateRandomToken());
		return accountRepository.save(account);
	}

	@Override
	public Account getByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

	@Override
	public long countAccount() {
		return accountRepository.count();
	}

}
