package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.DeveloperEducation;

public interface EducationService {

	List<DeveloperEducation> findByDevId(int userId);
	List<DeveloperEducation> index();
	DeveloperEducation create(int userId, DeveloperEducation edu, String username);
	DeveloperEducation edit(int userId, DeveloperEducation edu, String username, int eduId);
	void delete(int userId, String username, int eduId);
	
}
