package com.skilldistillery.enginex.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enginex.entities.DeveloperSkill;

public interface SkillRepository extends JpaRepository<DeveloperSkill, Integer> {

	List<DeveloperSkill> findByUserId(int id);
	
}
