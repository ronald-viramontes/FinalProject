package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.WorkExperience;

public interface ExperienceService {

	List<WorkExperience> findByDevId(int userId, String username);
	List<WorkExperience> index();
	WorkExperience create(WorkExperience exp, String username);
	WorkExperience edit(int userId, WorkExperience exp, String username, int expId);
	void delete(int userId, String username, int expId);
	
}
