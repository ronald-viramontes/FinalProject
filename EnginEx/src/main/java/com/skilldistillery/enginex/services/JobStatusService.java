package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.JobStatus;

public interface JobStatusService {
	
	List<JobStatus> getAll();
	JobStatus getById(int id);
	JobStatus create(JobStatus jobStatus, String username, int jobPostId);
	JobStatus update(JobStatus jobStatus, String username, int jobStatusId, int jobPostId);
	boolean destroy(String username, int jobStatusId, int jobPostId);
}
