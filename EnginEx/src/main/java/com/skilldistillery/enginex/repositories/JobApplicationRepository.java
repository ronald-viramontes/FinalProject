package com.skilldistillery.enginex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enginex.entities.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {

//	List<JobApplication> findByUserId(int id);

	
}
