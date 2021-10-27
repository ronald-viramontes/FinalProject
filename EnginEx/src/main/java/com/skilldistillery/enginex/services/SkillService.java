package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.DeveloperSkill;

public interface SkillService {
	
	List<DeveloperSkill> findAll();
	
}
