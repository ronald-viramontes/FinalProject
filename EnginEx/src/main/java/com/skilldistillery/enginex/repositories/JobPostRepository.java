package com.skilldistillery.enginex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enginex.entities.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost, Integer> {
}
