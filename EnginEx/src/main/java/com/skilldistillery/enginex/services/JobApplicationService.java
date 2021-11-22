package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.JobApplication;

public interface JobApplicationService {
	
	List<JobApplication> findAll();
	List<JobApplication> findByDevId(int userId);

	JobApplication create(int userId, int postId);
	JobApplication newApplication(String username, int postId);
	
	List<JobApplication> findAppsByUser(String username, int userId);
	boolean delete(int appId, int userId);
	JobApplication edit(int statusId, int appId, int userId);
	JobApplication findByAppId(int appId);
	JobApplication jobAppById(String username, int appId);
	
	JobApplication appDecision(String username, JobApplication app, int postId);
	JobApplication submitApplication(String username, JobApplication app);
	boolean destroyApp(String username, int appId);

}
