package com.skilldistillery.enginex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enginex.entities.DeveloperAccount;

public interface DeveloperAccountRepository extends JpaRepository<DeveloperAccount, Integer> {

	DeveloperAccount findByUsername(String username);
}
