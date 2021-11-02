package com.skilldistillery.enginex.services;

import com.skilldistillery.enginex.entities.JobDetail;

public interface JobDetailService {
	
	JobDetail getJobDetailById(int id);
	JobDetail create(String username, JobDetail jobDetail, int jobAppId);
	JobDetail update(String username, int userId, JobDetail jobDetail, int jobDetailId);
	void delete(String username, int userId, int jobDetailId);
	JobDetail getJobDetailByAppId(int appId, String username);

}
