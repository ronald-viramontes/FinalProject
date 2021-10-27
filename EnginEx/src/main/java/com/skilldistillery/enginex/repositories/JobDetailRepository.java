package com.skilldistillery.enginex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enginex.entities.JobDetail;

public interface JobDetailRepository extends JpaRepository<JobDetail, Integer> {

	JobDetail findById(int id);
}
