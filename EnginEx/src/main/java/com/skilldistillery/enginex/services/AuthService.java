package com.skilldistillery.enginex.services;

import com.skilldistillery.enginex.entities.User;

public interface AuthService {

	public User register(User user);

	public User getUser(String username);
}
