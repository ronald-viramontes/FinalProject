package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.JobApplication;

public interface JobApplicationService {
	
	List<JobApplication> findAll();
	List<JobApplication> findByDevId(int userId);

	JobApplication create(int userId, int postId);
	JobApplication createApp(String username, int postId);
	
	JobApplication newApplication(String username, JobApplication app, int postId);
	
	List<JobApplication> findAppsByUser(String username, int userId);
	boolean delete(int appId, int userId);
	JobApplication edit(int statusId, int appId, int userId);
	JobApplication findByAppId(int appId);
	
	
	JobApplication appDecision(String username, JobApplication app);
	JobApplication submitApplication(String username, JobApplication app);
	boolean destroyApp(String username, int appId);

}
