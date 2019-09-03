package com.resasoftware.nearme.services;

import com.resasoftware.nearme.entities.User;

public interface UserService extends CRUDService<User> {
	User validUser(String username, String password) throws Exception;
}
