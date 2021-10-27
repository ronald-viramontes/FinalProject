package com.skilldistillery.enginex.services;

import com.skilldistillery.enginex.entities.Developer;

public interface AuthService {

	public Developer register(Developer developer);
	public Developer getUser(String username);
}
