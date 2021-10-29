package com.skilldistillery.enginex.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enginex.entities.WorkExperience;

public interface ExperienceRepository extends JpaRepository<WorkExperience, Integer>{
	
	List<WorkExperience> findByDeveloperId(int id);

}
