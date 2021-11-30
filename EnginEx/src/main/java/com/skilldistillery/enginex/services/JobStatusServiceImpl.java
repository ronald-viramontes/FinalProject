package com.skilldistillery.enginex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.JobPost;
import com.skilldistillery.enginex.entities.JobStatus;
import com.skilldistillery.enginex.entities.User;
import com.skilldistillery.enginex.repositories.JobPostRepository;
import com.skilldistillery.enginex.repositories.JobStatusRepository;
import com.skilldistillery.enginex.repositories.UserRepository;

@Service
public class JobStatusServiceImpl implements JobStatusService{
	
	@Autowired
	private JobStatusRepository jobStatusRepo;
	
	@Autowired
	private JobPostRepository jobPostRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<JobStatus> getAll() {
		
		return jobStatusRepo.findAll();
	}

	@Override
	public JobStatus getById(int id) {
		
		Optional<JobStatus> opt = jobStatusRepo.findById(id);
		if(opt.isPresent()) {
			
			return opt.get();
		} else {
			return null;
		
		}
	}

	@Override
	public JobStatus create(JobStatus jobStatus, String username, int jobPostId) {
		User user = userRepo.findByUsername(username);
		
		Optional<JobPost> opt= jobPostRepo.findById(jobPostId);
		JobPost jobPost = opt.get();
		
		if(jobPost != null && jobPost.getUser().getId() == user.getId()) {
			jobStatus = jobStatusRepo.saveAndFlush(jobStatus);
			jobPost.setStatus(jobStatus);
			
			 jobPostRepo.saveAndFlush(jobPost);
			
			 return jobStatus;
			
		} else {
			return null;
		}
		
	}

	@Override
	public JobStatus update(JobStatus jobStatus, String username, int jobStatusId, int jobPostId) {
//		User user = userRepo.findByUsername(username);
		
		Optional<JobPost> opt= jobPostRepo.findById(jobPostId);
		JobPost jobPost = opt.get();
		Optional<JobStatus> optStat = jobStatusRepo.findById(jobStatusId);
		if (jobPost != null) {
			
			JobStatus dbJobStatus = optStat.get();
			dbJobStatus.setName(jobStatus.getName());
			jobStatusRepo.saveAndFlush(dbJobStatus);
		
			return dbJobStatus;
			
		} 
		return null;
	}

	@Override
	public boolean destroy(String username, int jobStatusId, int jobPostId) {
		User user = userRepo.findByUsername(username);
		
		Optional<JobStatus> optStat = jobStatusRepo.findById(jobStatusId);
		Optional<JobPost> opt= jobPostRepo.findById(jobPostId);
		JobPost jobPost = opt.get();
		
		if(jobPost != null && jobPost.getUser().getId() == user.getId()) {
			
			jobPost.setStatus(null);
			jobPostRepo.save(jobPost);
			
				JobStatus jobStatus = optStat.get();
				jobStatusRepo.delete(jobStatus);
				return true;
			} 
		return false;
		
		
	}


}
