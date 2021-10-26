package com.skilldistillery.enginex.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.DeveloperAccount;
import com.skilldistillery.enginex.repositories.DeveloperAccountRepository;

@Service
public class DeveloperAccountImpl implements DeveloperAccountService {
	@Autowired
	private DeveloperAccountRepository devRepo;

	@Override
	public DeveloperAccount getAcctByUsername(String username) {
		return devRepo.findByUsername(username);
	}

}
