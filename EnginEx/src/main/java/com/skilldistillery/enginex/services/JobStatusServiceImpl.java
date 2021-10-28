package com.skilldistillery.enginex.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.JobStatus;
import com.skilldistillery.enginex.entities.User;
import com.skilldistillery.enginex.repositories.JobStatusRepository;
import com.skilldistillery.enginex.repositories.UserRepository;

@Service
public class JobStatusServiceImpl implements JobStatusService{
	
	@Autowired
	private JobStatusRepository jobStatusRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<JobStatus> getAll() {
		
		return jobStatusRepo.findAll();
	}

	@Override
	public JobStatus getById(int id) {
		
		return jobStatusRepo.findById(id);
	}

	@Override
	public JobStatus create(JobStatus jobStatus, String username, int jobPostId) {
		User user = userRepo.findByUsername(username);
		
		jobStatusRepo.saveAndFlush(jobStatus);
		
		return jobStatus;
	}

	@Override
	public JobStatus update(JobStatus jobStatus, String username) {
		jobStatusRepo.save(jobStatus);
		return jobStatus;
	}

	@Override
	public boolean destroy(String username, int jobStatusId) {
		User user = userRepo.findByUsername(username);
		JobStatus jobStatus = jobStatusRepo.findById(jobStatusId);
		if (jobStatus == null) {
			return false;
		} else {
			jobStatusRepo.delete(jobStatus);
			return true;
		}
		
	}

	

	

}
