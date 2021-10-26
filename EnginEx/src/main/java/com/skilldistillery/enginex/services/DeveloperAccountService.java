package com.skilldistillery.enginex.services;

import com.skilldistillery.enginex.entities.DeveloperAccount;

public interface DeveloperAccountService {
	DeveloperAccount getAcctByName(String username);
}
