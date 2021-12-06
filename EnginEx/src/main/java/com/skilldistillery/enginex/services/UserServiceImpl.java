package com.skilldistillery.enginex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.User;
import com.skilldistillery.enginex.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	

	@Override
	public List<User> index() {
		return userRepo.findAll();
	}

	@Override
	public User show(int id) {
		Optional<User> receivedUser = userRepo.findById(id);
		return receivedUser.get();
	}

	@Override
	public User showUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> findBySkill(String skill) {
		skill = "%"+skill+"%";
		return userRepo.findBySkills_skillTitleLike(skill);
	}
	
	@Override
	public User disableMyAccount(String username, int userId) {
		User disabledUser = userRepo.findByUsername(username);
		
		disabledUser.setEnabled(false);
		
		return userRepo.saveAndFlush(disabledUser);
	}
	
	@Override
	public void destroyMyAccount(String principal, int userId) {
		User user = userRepo.findByUsername(principal);
		Optional<User> comp = userRepo.findById(userId);
		
		if (comp.isPresent() && comp.get().getId() == user.getId()) {
			User removeMe = comp.get(); 			
			userRepo.delete(removeMe);
		}
	}

	@Override
	public User updateMyAccount(String username, int id, User user) {
		Optional<User> u = userRepo.findById(id);
		User existingUser = u.get();
		User subUser = userRepo.findByUsername(username);
		if (subUser.getUsername().equals(existingUser.getUsername())) {
			if (existingUser != null) {
				existingUser.setUsername(user.getUsername());
				existingUser.setPassword(user.getPassword());
				existingUser.setEnabled(user.isEnabled());
				existingUser.setRole(user.getRole());
				existingUser.setFirstName(user.getFirstName());
				existingUser.setLastName(user.getLastName());
				existingUser.setEmail(user.getEmail());
				existingUser.setPhoneNumber(user.getPhoneNumber());
				existingUser.setImageUrl(user.getImageUrl());
				userRepo.saveAndFlush(existingUser);
				return existingUser;
			}
		}
		return null;
	}
	
	
	//ADMIN METHODS//
	
	@Override
	public void destroy(String username, int id) {
		User admin = userRepo.findByUsername(username);
		if(admin.getRole().equals("ADMIN")) {
			Optional<User> u = userRepo.findById(id);
			User delUser = u.get();
			userRepo.delete(delUser);
		}	
	}

	@Override
	public User enableOrDisableAccount(String username, User user) {

		user = userRepo.saveAndFlush(user);
		
		return user;
	}
}
