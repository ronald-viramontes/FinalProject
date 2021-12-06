package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.User;

public interface UserService {
	public List<User> index();
	public List<User> findBySkill(String skill);
	public User show(int id);
	public User showUsername(String username);
	
	//User Account Methods
	public User updateMyAccount(String username, int id, User user);
	public User disableMyAccount(String username, int userId);
	public void destroyMyAccount(String sysadmin, int userId);
	
	//ADMIN Methods
	public User enableOrDisableAccount(String username, User user);
	public void destroy(String username, int id);
	
}
