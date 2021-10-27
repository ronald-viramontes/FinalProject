package com.skilldistillery.enginex.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.JobPost;
import com.skilldistillery.enginex.entities.User;
import com.skilldistillery.enginex.repositories.JobPostRepository;
import com.skilldistillery.enginex.repositories.UserRepository;

@Service
public class JobPostServiceImpl implements JobPostService {
	
	@Autowired
	private JobPostRepository jobPostRepo;
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<JobPost> index() {
		// TODO Auto-generated method stub
		
		
		return jobPostRepo.findAll();
	}
	

}
