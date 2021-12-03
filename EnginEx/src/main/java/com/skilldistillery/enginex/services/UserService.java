package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.User;

public interface UserService {
//	User getUserByName(String username);
	public List<User> index();

	public User show(int id);
	
	public User showUsername(String username);

	public User update(String username, int id, User user);

	public void destroy(String username, int id);
	
	public List<User> findBySkill(String skill);
	
	public User disableAccount(String username, int userId);
	
	public boolean removeUserAccount(String sysadmin, String username, int userId);
	
	public User enableOrDisableAccount(String username, int userId, User user);
}
