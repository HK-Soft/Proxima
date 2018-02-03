package com.proximar.services.userServices;

import com.proximar.core.model.User;

public interface UserServices {

	public User create(String username, String password);

	public User getByUsername(String username);

}
