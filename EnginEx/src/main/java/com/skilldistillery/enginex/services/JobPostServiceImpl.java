package com.skilldistillery.enginex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.JobApplication;
import com.skilldistillery.enginex.entities.JobPost;
import com.skilldistillery.enginex.repositories.JobApplicationRepository;
import com.skilldistillery.enginex.repositories.JobPostRepository;

@Service
public class JobPostServiceImpl implements JobPostService {

	@Autowired
	private JobPostRepository jobPostRepo;
	@Autowired
	private JobApplicationRepository jobAppRepo;
	
	@Override
	public List<JobPost> index() {
		return jobPostRepo.findAll();
	}

	@Override
	public JobPost show(int id) {
		Optional<JobPost> receivedJob = jobPostRepo.findById(id);
		return receivedJob.get();
	}

	@Override
	public JobPost update(int id, JobPost jobPost) {
		// TODO Auto-generated method stub
		Optional<JobPost> j = jobPostRepo.findById(id);
		JobPost existingJobPost = j.get();
		if(existingJobPost != null) {
			existingJobPost.setJobRequirements(jobPost.getJobRequirements());
			existingJobPost.setStartDate(jobPost.getStartDate());
			existingJobPost.setCompletionDate(jobPost.getCompletionDate());
			existingJobPost.setDevelopersNeeded(jobPost.getDevelopersNeeded());
			existingJobPost.setJobActive(true);
			existingJobPost.setStartDate(jobPost.getStartDate());
			existingJobPost.setDateClosed(jobPost.getDateClosed());
			existingJobPost.setStatus(jobPost.getStatus());
			jobPostRepo.saveAndFlush(existingJobPost);
			return existingJobPost;
		}

		return null;
	}

	@Override
	public void destroy(int id) {
		Optional<JobPost> j = jobPostRepo.findById(id);
		JobPost delJob = j.get();
		for (JobApplication app : delJob.getApplications()) {
			jobAppRepo.delete(app);
		}
		jobPostRepo.delete(delJob);

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

}
