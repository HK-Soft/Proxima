package com.proximar.services.userServices;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.proximar.core.model.User;


public interface UserRepository extends CrudRepository<User, UUID> {
	User findByAccountUsername(String username);
}
