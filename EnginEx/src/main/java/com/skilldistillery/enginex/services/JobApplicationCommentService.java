package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.JobApplicationComment;

public interface JobApplicationCommentService {
	
	List<JobApplicationComment> findAll();
//	List<JobApplicationComment> findByUserId(String username, int userId);
	List<JobApplicationComment> findByUserIdAndAppId(String username, int userId, int appId);
	JobApplicationComment findByCommentId(String username, int userId, int commentId);
	JobApplicationComment create(String username, JobApplicationComment comment, int userId, int appId);
	JobApplicationComment createReply(String username, JobApplicationComment comment, int userId, int appId, int commentId);
	
	void delete(String username, int commentId, int appId, int userId);
	JobApplicationComment edit(String username, JobApplicationComment comment, int commentId, int appId, int userId);

}
