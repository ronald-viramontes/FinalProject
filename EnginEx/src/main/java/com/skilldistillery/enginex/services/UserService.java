package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.User;

public interface UserService {
//	User getUserByName(String username);
	List<User> index();
	User show(int id);
	User create(User user);
}
