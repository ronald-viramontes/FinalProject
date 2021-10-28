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
	public User create(User user) {
		// TODO Auto-generated method stub
		User newUser = user;
		Developer dev = new Developer();
		newUser.setDeveloper(dev);
		newUser.getDeveloper().setFirstName("jacob");
		newUser.getDeveloper().setLastName("Tweedy");
		newUser.getDeveloper().setEmail("jacob.tweedy@gmail.com");
		newUser.getDeveloper().setUser(newUser);
		newUser = userRepo.saveAndFlush(user);
		return newUser;
	}

}
