package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.DeveloperSkill;

public interface SkillService {
	
	List<DeveloperSkill> findByDevId(int userId, String username);
	List<DeveloperSkill> index();
	DeveloperSkill create(DeveloperSkill newSkill, String username);
	DeveloperSkill edit(int userId, DeveloperSkill skill, String username, int skillId);
	void delete(int userId, String username, int skillId);
	
}
