package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.JobPost;

public interface JobPostService {
	public List<JobPost> index();
	
	public List<JobPost> findByClientId(int userId);

	public JobPost show(int id);
	
	public JobPost create(JobPost jobPost);
	
	public JobPost update(int id, JobPost jobPost);
	
	public void destroy(int id);
	
	public List<JobPost> findByStatus(String status);
}
