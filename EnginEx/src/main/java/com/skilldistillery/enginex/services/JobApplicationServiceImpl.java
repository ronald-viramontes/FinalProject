package com.skilldistillery.enginex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.Developer;
import com.skilldistillery.enginex.entities.JobApplication;
import com.skilldistillery.enginex.entities.JobPost;
import com.skilldistillery.enginex.repositories.DeveloperRepository;
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
	private DeveloperRepository devRepo;
	@Autowired
	private JobPostRepository postRepo;
	
	
	@Override
	public List<JobApplication> findAll() {
		return appRepo.findAll();
	}


	@Override
	public List<JobApplication> findByDevId(int devId) {
		return appRepo.findByDeveloperId(devId);
	}


	@Override
	public JobApplication create(JobApplication app, int userId, int postId) {
		Developer dev = userRepo.findById(userId).get().getDeveloper();
		JobPost post = postRepo.findById(postId).get();
		app.setDeveloper(dev);
		app.setJobPost(post);
		return appRepo.saveAndFlush(app);
	}


	@Override
	public boolean delete(int appId, int userId) {
		if(appRepo.findById(appId).isPresent()) {
			JobApplication app = appRepo.findById(appId).get();
			if(app.getDeveloper().getUser().getId() == userId) {
				appRepo.delete(app);
				return true;
			}
		}
		return false;
	}


	@Override
	public JobApplication edit(JobApplication edit, int appId, int userId) {
		Optional<JobApplication> opt = appRepo.findById(appId);
		if(opt.isPresent() && opt.get().getJobPost().getClient().getUser().getId() == userId) {
			JobApplication app = appRepo.findById(appId).get();
			app.setApproved(edit.isApproved());
			app.setStatus(edit.getStatus());
			return appRepo.saveAndFlush(app);
		}
		return null;
	}
}