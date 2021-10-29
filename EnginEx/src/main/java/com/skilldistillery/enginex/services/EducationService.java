package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.DeveloperEducation;

public interface EducationService {

	List<DeveloperEducation> findByDevId(int devId, String username);
	List<DeveloperEducation> index();
	DeveloperEducation create(int devId, DeveloperEducation edu, String username);
	DeveloperEducation edit(int devId, DeveloperEducation edu, String username, int eduId);
	void delete(int devId, String username, int eduId);
	
}
