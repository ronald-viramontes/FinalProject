package com.skilldistillery.enginex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enginex.entities.JobType;

public interface JobTypeRepository extends JpaRepository<JobType, Integer> {

}
