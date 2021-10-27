package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.Developer;

public interface DeveloperService {
	
	List<Developer> findAll();
	Developer findById(int id);
	Developer create(Developer dev, int userId);
	Developer edit(Developer edit, int userId, int devId);
	boolean delete(String username, int id);
	
}
