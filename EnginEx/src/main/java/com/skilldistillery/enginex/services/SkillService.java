package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.DeveloperSkill;

public interface SkillService {
	
	List<DeveloperSkill> findAll();
	List<DeveloperSkill> findByDevId(int id);
	DeveloperSkill create(DeveloperSkill newSkill, String username);
	DeveloperSkill edit(DeveloperSkill skill, int userId, int skillId);
	boolean delete(int skillId, int userId);
	
}
