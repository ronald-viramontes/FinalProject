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
	public List<JobApplication> findAppsByUser(String username, int userId) {
		User user = userRepo.findByUsername(username);
		if(user.getId() == userId) {
			return appRepo.findByUserId(userId);
			
		}
			return null;
	}
	
	@Override
	public JobApplication findByAppId(int appId) {
		JobApplication jobApp = appRepo.findById(appId);
		return jobApp;
	}
	

	
	@Override
	public JobApplication create(int userId, int postId) {
		User user = userRepo.findById(userId).get();
		Optional<JobPost> opt = postRepo.findById(postId);
		JobApplication app = new JobApplication();
		app.setUser(user);
		app.setJobPost(opt.get());
		app.setStatus("Open");
		app.setDate(LocalDate.now());
		System.out.println(app);
		return appRepo.saveAndFlush(app);
	}

	@Override
	public JobApplication edit(int statusId, int appId, int userId) {
		JobApplication opt = appRepo.findById(appId);
		if( opt.getJobPost().getUser().getId() == userId) {
			JobApplication app = appRepo.findById(appId);
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
	public boolean delete(int appId, int userId) {
		if(appRepo.findById(appId) != null) {
			JobApplication app = appRepo.findById(appId);
			if(app.getUser().getId() == userId) {
				appRepo.delete(app);
				return true;
			}
		}
		return false;
	}

	@Override
	public JobApplication submitApplication(String username, JobApplication app) {
		User user = userRepo.findByUsername(username);
		if(user.getId() == app.getUser().getId()) {
			return appRepo.saveAndFlush(app);
		}
		return null;
	}

	@Override
	public JobApplication appDecision(String username, JobApplication app) {
		User poster = userRepo.findByUsername(username);
		if(app.getJobPost().getUser().getId() == poster.getId() ) {
			
			return app = appRepo.saveAndFlush(app);
						
		} return null;

		
	}

	@Override
	public boolean destroyApp(String username, int appId) {
		User user = userRepo.findByUsername(username);
		JobApplication opt = appRepo.findById(appId);
		if( opt.getUser().getId() == user.getId()) {
			
			appRepo.delete(opt);
			return true;
		}		
		return false;
	}


	@Override
	public JobApplication newApplication(String username, JobApplication app, int postId) {
		User user = userRepo.findByUsername(username);
		
		Optional<JobPost> opt = postRepo.findById(postId);	
		
		if(opt.isPresent()) {
		app.setApproved(false);
		app.setStatus("Pending");
		app.setDate(LocalDate.now());
		app.setJobPost(opt.get());
		app.setUser(user);
		
		return appRepo.saveAndFlush(app);
		}
		return null;
	}


	@Override
	public JobApplication createApp(String username, int postId) {
		User user = userRepo.findByUsername(username);
		Optional<JobPost> opt = postRepo.findById(postId);
		if(opt.isPresent()) {
			JobApplication jobApp = new JobApplication();
			jobApp.setUser(user);
			jobApp.setJobPost(opt.get());
			jobApp.setApproved(false);
			jobApp.setStatus("Pending");
			jobApp.setDate(LocalDate.now());
			
			return appRepo.saveAndFlush(jobApp);
		}
		return null;
	}
}