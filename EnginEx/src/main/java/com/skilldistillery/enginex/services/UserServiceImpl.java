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
	public void destroy(String username, int id) {
		Optional<User> u = userRepo.findById(id);
		User delUser = u.get();
//		userRepo.delete(delUser);
		delUser.setEnabled(false);
		if (delUser.getUsername().equals(username)) {
			userRepo.delete(delUser);
		}

	}

	@Override
	public User update(String username, int id, User user) {
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

	@Override
	public User showUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> findBySkill(String skill) {
		return userRepo.findBySkills_skillTitleLike(skill);
	}

}
