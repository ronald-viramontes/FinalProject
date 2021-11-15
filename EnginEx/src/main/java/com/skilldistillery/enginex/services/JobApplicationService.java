package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.JobApplication;

public interface JobApplicationService {
	
	List<JobApplication> findAll();
	List<JobApplication> findByDevId(int userId);
	JobApplication create(int userId, int postId);
	boolean delete(int appId, int userId);
	JobApplication edit(int statusId, int appId, int userId);

}
