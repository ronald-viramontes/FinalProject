package com.skilldistillery.enginex.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enginex.entities.DeveloperSkill;
import com.skilldistillery.enginex.entities.JobApplication;

public interface JobApplicationRepo extends JpaRepository<JobApplication, Integer> {

//	List<DeveloperSkill> findByDeveloperId(int id);
	
}
