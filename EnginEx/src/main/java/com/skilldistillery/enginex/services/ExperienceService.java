package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.WorkExperience;

public interface ExperienceService {

	List<WorkExperience> findByDevId(int devId, String username);
	List<WorkExperience> index();
	WorkExperience create(WorkExperience exp, String username);
	WorkExperience edit(int devId, WorkExperience exp, String username, int expId);
	void delete(int devId, String username, int expId);
	
}
