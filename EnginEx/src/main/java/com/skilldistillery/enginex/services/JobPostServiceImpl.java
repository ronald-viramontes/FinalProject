package com.skilldistillery.enginex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.JobPost;
import com.skilldistillery.enginex.repositories.JobPostRepository;
import com.skilldistillery.enginex.repositories.UserRepository;

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
		
		return null;
	}

	@Override
	public void destroy(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public JobPost create(JobPost jobPost) {
		// TODO Auto-generated method stub
		jobPost = jobPostRepo.saveAndFlush(jobPost);
		return jobPost;
	}

}
