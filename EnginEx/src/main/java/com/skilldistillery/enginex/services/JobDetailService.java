package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.JobDetail;

public interface JobDetailService {
	
	JobDetail getJobDetailById(int id);
	List<JobDetail> getAllDetails();
	JobDetail create(String username, JobDetail jobDetail, int jobAppId);
	JobDetail update(String username, JobDetail jobDetail, int jobAppId);
	boolean delete(String username, int id);

}
