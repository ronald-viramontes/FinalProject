package com.skilldistillery.enginex.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.JobApplication;
import com.skilldistillery.enginex.entities.JobPost;
import com.skilldistillery.enginex.entities.JobStatus;
import com.skilldistillery.enginex.entities.User;
import com.skilldistillery.enginex.repositories.JobApplicationRepository;
import com.skilldistillery.enginex.repositories.JobPostRepository;
import com.skilldistillery.enginex.repositories.JobStatusRepository;
import com.skilldistillery.enginex.repositories.UserRepository;

@Service
public class JobPostServiceImpl implements JobPostService {

	@Autowired
	private JobPostRepository jobPostRepo;
	@Autowired
	private JobApplicationRepository jobAppRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private JobStatusRepository statusRepo;
	
	
	@Override
	public List<JobPost> index() {
		return jobPostRepo.findAll();
	}

	@Override
	public JobPost show(int postId) {
		Optional<JobPost> receivedJob = jobPostRepo.findById(postId);
		if(receivedJob.isPresent()) {
			return receivedJob.get();			
		} 
		return null;
	}

	@Override
	public JobPost update(int id, JobPost jobPost) {
		
		Optional<JobPost> opt = jobPostRepo.findById(id);
		JobPost existingJobPost = opt.get();
		if(existingJobPost != null) {
			existingJobPost.setJobRequirements(jobPost.getJobRequirements());
			existingJobPost.setStartDate(jobPost.getStartDate());
			existingJobPost.setCompletionDate(jobPost.getCompletionDate());
			existingJobPost.setDevelopersNeeded(jobPost.getDevelopersNeeded());
			existingJobPost.setJobActive(jobPost.isJobActive());
			existingJobPost.setDateClosed(jobPost.getDateClosed());
			existingJobPost.setStatus(jobPost.getStatus());
			existingJobPost = jobPostRepo.saveAndFlush(existingJobPost);
			return existingJobPost;
		}

		return null;
	}

	@Override
	public void destroy(int postId) {
		Optional<JobPost> opt = jobPostRepo.findById(postId);
		JobPost delJob = opt.get();
		if(delJob != null) {
			for (JobApplication app : delJob.getApplications()) {
				jobAppRepo.delete(app);
			}
			jobPostRepo.delete(delJob);
		}

	}

	@Override
	public JobPost create(JobPost jobPost) {
		jobPost = jobPostRepo.saveAndFlush(jobPost);
		
		return jobPost;
	}

	@Override
	public List<JobPost> findByClientId(int userId) {
		return jobPostRepo.findByUserId(userId);
	}

	@Override
	public List<JobPost> findByStatus(String status) {
		return jobPostRepo.findByStatusNameLike(status);
	}

	@Override
	public List<JobPost> findByKeyword(String keyword) {
		return jobPostRepo.findByJobRequirementsContaining(keyword);
	}

	@Override
	public JobPost createPost(String username, JobPost jobPost) {
		User user = userRepo.findByUsername(username);
			JobStatus status = statusRepo.getById(1);
		
			jobPost.setUser(user);
			jobPost.setDatePosted(LocalDate.now());
			jobPost.setJobActive(true);
			jobPost.setStatus(status);	
			jobPost = jobPostRepo.saveAndFlush(jobPost);
			return jobPost;
	
	}
	
	@Override
	public JobPost updatePost(String username, JobPost jobPost, int postId) {
		User user = userRepo.findByUsername(username);
		Optional<JobPost> opt = jobPostRepo.findById(postId);
		JobPost dbJob = opt.get();
		
		if(dbJob != null && user.getId() == dbJob.getUser().getId()) {
			if (jobPost.getDateClosed() != null) {
				dbJob.setDateClosed(jobPost.getDateClosed());
				
			}
			
			dbJob.setJobRequirements(jobPost.getJobRequirements());
			dbJob.setStartDate(jobPost.getStartDate());
			dbJob.setCompletionDate(jobPost.getCompletionDate());
			dbJob.setDevelopersNeeded(jobPost.getDevelopersNeeded());
			dbJob.setJobActive(jobPost.isJobActive());
			dbJob.setType(jobPost.getType());
			dbJob.setStatus(jobPost.getStatus());
						
			dbJob = jobPostRepo.saveAndFlush(dbJob);
			return dbJob;
			
		}
		return null;
	}
	
	@Override
	public void destroyMyPost(String username, int postId) {
		Optional<JobPost> opt = jobPostRepo.findById(postId);
		JobPost job = opt.get();
		if(job != null) {
			for (JobApplication app : job.getApplications()) {
				
				jobAppRepo.delete(app);
			}

			jobPostRepo.delete(job);
			
		}
	} 

}
