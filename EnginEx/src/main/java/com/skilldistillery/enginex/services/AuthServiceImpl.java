package com.skilldistillery.enginex.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
		User test = userRepo.findByUsername(user.getUsername());
		System.out.println("----------------------------------------------------------------------------"+test);
		if (test.getUsername().equals(user.getUsername())) {
			return null;
		} else {
			String encodedPW = encoder.encode(user.getPassword());
			user.setPassword(encodedPW); // only persist encoded password
			System.out.println(user);
			user.setEnabled(true);
			user.setRole("standard");
			user.getDeveloper().setUser(user);
			user.getClient().setUser(user);
			user = userRepo.saveAndFlush(user);
			return user;

		}
	}

	@Override
	public User getUser(String username) {
		User user = userRepo.findByUsername(username);
		if (user.isEnabled()) {
			return user;
		} else {
			return null;
		}
	}

}
