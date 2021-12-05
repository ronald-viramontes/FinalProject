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
		Optional<User> opt = userRepo.findById(userId);
		if(opt.isPresent()) {
			return opt.get().getApplications();
		}
		return null;
	
	}

	@Override
	public List<JobApplication> findAppsByUser(String username, int userId) {
		User user = userRepo.findByUsername(username);
		
			return user.getApplications();
//		if(user.getId() == userId) {
//			return appRepo.findByUserId(userId);
//			
//		}
//			return null;
	}
	
	@Override
	public JobApplication findByAppId(int appId) {
		Optional<JobApplication> jobApp = appRepo.findById(appId);
				JobApplication job = jobApp.get();
				return job;
	}
	

	
	@Override
	public JobApplication create(int userId, int postId) {
		User user = userRepo.findById(userId).get();
		Optional<JobPost> opt = postRepo.findById(postId);
		JobApplication app = new JobApplication();
		app.setUser(user);
		app.setJobPost(opt.get());
		app.setStatus("Pending Review");
		app.setDate(LocalDate.now());
		System.out.println(app);
		return appRepo.saveAndFlush(app);
	}

	@Override
	public JobApplication edit(int statusId, int appId, int userId) {
		Optional<JobApplication> opt = appRepo.findById(appId);
		if( opt.isPresent() && opt.get().getJobPost().getUser().getId() == userId) {
			JobApplication app = opt.get();
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
			Optional<JobApplication> app = appRepo.findById(appId);
			if(app.get().getUser().getId() == userId) {
				appRepo.delete(app.get());
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
		
			
			return app = appRepo.saveAndFlush(app);
						
		

		
	}

	@Override
	public boolean destroyApp(String username, int appId) {
		User user = userRepo.findByUsername(username);
		Optional<JobApplication> opt = appRepo.findById(appId);
		if( opt.get().getUser().getId() == user.getId()) {
			
			appRepo.delete(opt.get());
			return true;
		}		
		return false;
	}


	@Override
	public JobApplication newApplication(String username, JobApplication app, int postId) {
		User user = userRepo.findByUsername(username);
		
		Optional<JobPost> opt = postRepo.findById(postId);	
		
		if(opt.isPresent()) {
			app.setUser(user);
			app.setJobPost(opt.get());
			app.setStatus("Pending Review");
			app.setDate(LocalDate.now());
		
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
			jobApp.setStatus("Pending Review");
			jobApp.setDate(LocalDate.now());
			
			return appRepo.saveAndFlush(jobApp);
		}
		return null;
	}
}