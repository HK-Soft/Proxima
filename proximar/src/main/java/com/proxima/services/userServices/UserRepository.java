package com.proxima.services.userServices;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.proxima.core.model.User;


public interface UserRepository extends CrudRepository<User, UUID> {
	User findByAccountUsername(String username);
}
