package com.skilldistillery.enginex.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.DeveloperAccount;
import com.skilldistillery.enginex.repositories.DeveloperAccountRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private DeveloperAccountRepository devRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public DeveloperAccount register(DeveloperAccount developer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeveloperAccount getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
