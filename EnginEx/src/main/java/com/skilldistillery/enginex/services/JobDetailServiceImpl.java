package com.skilldistillery.enginex.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.JobApplication;
import com.skilldistillery.enginex.entities.JobDetail;
import com.skilldistillery.enginex.entities.User;
import com.skilldistillery.enginex.repositories.JobApplicationRepository;
import com.skilldistillery.enginex.repositories.JobDetailRepository;
import com.skilldistillery.enginex.repositories.UserRepository;

@Service
public class JobDetailServiceImpl implements JobDetailService {

	@Autowired
	private JobDetailRepository jobDetailRepo;

	@Autowired
	private JobApplicationRepository jobAppRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public JobDetail getJobDetailById(int id) {

		Optional<JobDetail> opt = jobDetailRepo.findById(id);

		if (opt.isPresent()) {
			return opt.get();

		} else {
			return null;
		}

	}

	
	@Override
	public JobDetail create(String username, JobDetail jobDetail, int jobAppId) {
		User user = userRepo.findByUsername(username);

		Optional<JobApplication> ja = jobAppRepo.findById(jobAppId);

		if (ja.isPresent() && ja.get().getJobPost().getUser().getId() == user.getId()) {
			JobApplication jobApplication = ja.get();
			jobDetail = jobDetailRepo.saveAndFlush(jobDetail);
			jobApplication.setDetail(jobDetail);
			jobAppRepo.saveAndFlush(jobApplication);
			return jobDetail;
		}
		return null;

	}

	@Override
	public JobDetail update(String username, int userId, JobDetail jobDetail, int jobDetailId) {
		User user = userRepo.findByUsername(username);
		JobDetail detailDb = null;
		Optional<JobDetail> opt = jobDetailRepo.findById(jobDetailId);
		if(userId == user.getId()) {
			if(opt.isPresent()) {
				detailDb = opt.get();
				detailDb.setComment(jobDetail.getComment());
				detailDb.setFinishDate(jobDetail.getFinishDate());
				detailDb.setStartDate(jobDetail.getStartDate());
				detailDb.setRating(jobDetail.getRating());
				
				detailDb = jobDetailRepo.saveAndFlush(detailDb);
				
			}
			return detailDb;
		}
		return null;
		

	}

	@Override
	public void delete(String username, int userId, int jobDetailId) {
		User user = userRepo.findByUsername(username);
		
		JobDetail jobDetail = null;
		
		Optional<JobDetail> opt = jobDetailRepo.findById(jobDetailId);
		
		
		if(userId == user.getId()) {
			if(opt.isPresent()) {
				jobDetail = opt.get();
				jobDetailRepo.delete(jobDetail);
			}
		
		}
				
	}


	@Override
	public JobDetail getJobDetailByAppId(int appId, String username) {
		User user = userRepo.findByUsername(username);
		JobDetail jobDetail = jobDetailRepo.findByApplication_Id(appId);
		if(jobDetail.getApplication().getUser().getId() == user.getId()) {
			
			return jobDetail;
		}
		return null;
	}
}
