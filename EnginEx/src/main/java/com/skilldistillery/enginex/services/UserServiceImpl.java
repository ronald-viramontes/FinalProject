package com.skilldistillery.enginex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.Developer;
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

//
//	@Override
//	public User getUserByName(String username) {
//		return userRepo.findByUsername(username);
//	}

	@Override
	public void destroy(int id) {
		Optional<User> u = userRepo.findById(id);
		User delUser = u.get();
//		userRepo.delete(delUser);
		delUser.setEnabled(false);

	}

	@Override
	public User update(int id, User user) {
		// TODO Auto-generated method stub
		Optional<User> u = userRepo.findById(id);
		User existingUser = u.get();
		if (existingUser != null) {
			existingUser.setUsername(user.getUsername());
			existingUser.setPassword(user.getPassword());
			existingUser.setEnabled(user.isEnabled());
			existingUser.setRole(user.getRole());
			userRepo.saveAndFlush(existingUser);
			return existingUser;
		}
		return null;
	}

}
