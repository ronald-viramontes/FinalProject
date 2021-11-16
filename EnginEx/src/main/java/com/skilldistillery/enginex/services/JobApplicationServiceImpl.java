package com.skilldistillery.enginex.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.JobApplication;
import com.skilldistillery.enginex.entities.JobPost;
import com.skilldistillery.enginex.entities.User;
import com.skilldistillery.enginex.repositories.JobApplicationRepository;
import com.skilldistillery.enginex.repositories.JobPostRepository;
import com.skilldistillery.enginex.repositories.UserRepository;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {
	
	@Autowired
	private JobApplicationRepository appRepo;
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private JobPostRepository postRepo;
	
	
	@Override
	public List<JobApplication> findAll() {
		return appRepo.findAll();
	}


	@Override
	public List<JobApplication> findByDevId(int userId) {
		return appRepo.findByUserId(userId);
	}


	@Override
	public JobApplication create(int userId, int postId) {
		User user = userRepo.findById(userId).get();
		JobPost post = postRepo.findById(postId).get();
		JobApplication app = new JobApplication();
		app.setUser(user);
		app.setJobPost(post);
		app.setStatus("Open");
		app.setDate(LocalDate.now());
		System.out.println(app);
		return appRepo.saveAndFlush(app);
	}


	@Override
	public boolean delete(int appId, int userId) {
		if(appRepo.findById(appId).isPresent()) {
			JobApplication app = appRepo.findById(appId).get();
			if(app.getUser().getId() == userId) {
				appRepo.delete(app);
				return true;
			}
		}
		return false;
	}


	@Override
	public JobApplication edit(int statusId, int appId, int userId) {
		Optional<JobApplication> opt = appRepo.findById(appId);
		if(opt.isPresent() && opt.get().getJobPost().getUser().getId() == userId) {
			JobApplication app = appRepo.findById(appId).get();
			if(statusId == 1) {
				app.setApproved(true);
				app.setStatus("Approved");
				
			}else {
				app.setApproved(false);
				app.setStatus("Declined");
				
			}
			return appRepo.saveAndFlush(app);
		}
		return null;
	}


	@Override
	public JobApplication findByAppId(int appId) {
		Optional <JobApplication> jobApp = appRepo.findById(appId);
		return jobApp.get();
	}
}