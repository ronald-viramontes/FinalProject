package com.skilldistillery.enginex.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enginex.entities.JobApplicationComment;

public interface JobApplicationCommentRepository extends JpaRepository<JobApplicationComment, Integer> {

//	List<JobApplicationComment> findByUser_Id(int id);
	List<JobApplicationComment> findByApplication_Id(int appId);
	JobApplicationComment findById(int id);
	
}
