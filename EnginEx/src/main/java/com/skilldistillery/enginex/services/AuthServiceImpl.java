package com.skilldistillery.enginex.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.Developer;
import com.skilldistillery.enginex.repositories.DeveloperRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private DeveloperRepository devRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public Developer register(Developer developer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Developer getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
