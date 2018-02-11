package com.proxima.services.userServices;

import com.proxima.core.model.User;

public interface UserServices {

	public User create(String username, String password);

	public User getByUsername(String username);

}
