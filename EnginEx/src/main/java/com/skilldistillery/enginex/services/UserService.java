package com.skilldistillery.enginex.services;

import com.skilldistillery.enginex.entities.User;

public interface UserService {
	User getUserByName(String username);
}
