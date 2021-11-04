package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.JobApplication;
import com.skilldistillery.enginex.entities.User;

public interface JobApplicationService {
	
	List<JobApplication> findAll();
	List<JobApplication> findByDevId(int userId);
	JobApplication create(int userId, int postId);
	boolean delete(int appId, int userId);
	JobApplication edit(int statusId, int appId, int userId);

}
