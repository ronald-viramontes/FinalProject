package com.skilldistillery.enginex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enginex.entities.Developer;

public interface DeveloperRepository extends JpaRepository<Developer, Integer> {

	
}
