package com.skilldistillery.enginex.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enginex.entities.DeveloperEducation;

public interface EducationRepository extends JpaRepository<DeveloperEducation, Integer>{
	
	List<DeveloperEducation> findByUserId(int id);

}
