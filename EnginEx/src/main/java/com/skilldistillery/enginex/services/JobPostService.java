package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.JobPost;

public interface JobPostService {
	public List<JobPost> index();
	public List<JobPost> findByClientId(int userId);
	public List<JobPost> findByStatus(String status);
	public List<JobPost> findByKeyword(String keyword);

	public JobPost show(int postId);
	
	public JobPost create(JobPost jobPost);	
	public JobPost update(int postId, JobPost jobPost);
	public void destroy(int postId);
	
	public JobPost createPost(String username, JobPost jobPost);
	public JobPost updatePost(String username, JobPost jobPost, int postId);
	public boolean destroyMyPost(String username, int postId);
	
}
