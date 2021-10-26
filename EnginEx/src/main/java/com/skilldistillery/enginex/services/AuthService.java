package com.skilldistillery.enginex.services;

import com.skilldistillery.enginex.entities.DeveloperAccount;

public interface AuthService {

	public DeveloperAccount register(DeveloperAccount developer);
	public DeveloperAccount getUser(String username);
}
