package com.skilldistillery.enginex.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.Client;
import com.skilldistillery.enginex.entities.Developer;
import com.skilldistillery.enginex.entities.User;
import com.skilldistillery.enginex.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public User register(User user) {
		String encodedPW = encoder.encode(user.getPassword());
		user.setPassword(encodedPW); // only persist encoded password
		System.out.println(user);

		// set other fields to default values
		//TODO:
		//Developer dev = new Developer();
		user.setEnabled(true);
		user.setRole("standard");
		//user.setDeveloper(dev);
		//user.getDeveloper().setFirstName("jacob");
		//user.getDeveloper().setLastName("Tweedy");
		//user.getDeveloper().setEmail("jacob.tweedy@gmail.com");
		user.getDeveloper().setUser(user);
		Client cl = new Client();
		user.setClient(cl);
		user.getClient().setFirstName(user.getDeveloper().getFirstName());
		user.getClient().setLastName(user.getDeveloper().getLastName());
		user.getClient().setEmail(user.getDeveloper().getEmail());
		user.getClient().setUser(user);
		user = userRepo.saveAndFlush(user);
		userRepo.saveAndFlush(user);
		return user;
	}

	@Override
	public User getUser(String username) {
		
		return userRepo.findByUsername(username);
	}


}
