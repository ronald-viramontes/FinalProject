package com.skilldistillery.enginex.services;

import java.time.LocalDate;
import java.util.List;

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
		JobPost post = postRepo.findById(postId);
		JobApplication app = new JobApplication();
		app.setUser(user);
		app.setJobPost(post);
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
	public JobApplication newApplication(String username, int postId) {
		JobApplication app = new JobApplication();
		User user = userRepo.findByUsername(username);
		JobPost jobPost = postRepo.findById(postId);
		
			app.setApproved(false);
			app.setStatus("Pending Review");
			app.setDate(LocalDate.now());
			app.setJobPost(jobPost);
			app.setUser(user);
			return appRepo.saveAndFlush(app);
		
	}
}