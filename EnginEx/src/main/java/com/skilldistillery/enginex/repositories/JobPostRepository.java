package com.skilldistillery.enginex.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enginex.entities.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost, Integer> {
	
	List<JobPost> findByUserId(int userId);
	List<JobPost> findByStatusNameLike(String status);
	List<JobPost> findByJobRequirementsContaining(String keyword);
	Optional<JobPost> findById(int postId);
}
