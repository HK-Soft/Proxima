package com.proximar.services.userServices;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proximar.core.model.User;
import com.proximar.services.accountServices.AccountServices;

@Service
public class IUserServices implements UserServices {

	@Transactional(rollbackOn = Exception.class)
	public User create(String username, String password) {
		User user = new User();
		user.setAccount(accountServices.create(username, password));
		return userRepository.save(user);
	}

	public User getByUsername(String username) {
		return userRepository.findByAccountUsername(username);
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountServices accountServices;
}
