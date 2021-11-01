package com.skilldistillery.enginex.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.JobPost;
import com.skilldistillery.enginex.repositories.JobPostRepository;

@Service
public class JobPostServiceImpl implements JobPostService {

	@Autowired
	private JobPostRepository jobPostRepo;

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
			existingJobPost.setStartDate(LocalDate.now());
			existingJobPost.setDateClosed(jobPost.getDateClosed());
			jobPostRepo.saveAndFlush(existingJobPost);
			return existingJobPost;
		}

		return null;
	}

	@Override
	public void destroy(int id) {
		Optional<JobPost> j = jobPostRepo.findById(id);
		JobPost delJob = j.get();
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

}
