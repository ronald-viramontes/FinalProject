package com.skilldistillery.enginex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.JobApplication;
import com.skilldistillery.enginex.entities.JobDetail;
import com.skilldistillery.enginex.repositories.JobApplicationRepository;
import com.skilldistillery.enginex.repositories.JobDetailRepository;

@Service
public class JobDetailServiceImpl implements JobDetailService {

	@Autowired
	private JobDetailRepository jdRepo;
	
	@Autowired
	private JobApplicationRepository jobAppRepo;
	
	@Override
	public JobDetail getJobDetailById(int id) {
		
		Optional <JobDetail> opt = jdRepo.findById(id);
		
		if(opt.isPresent()) {
			return opt.get();
				
		} else {
			return null;
		}
		
		
	}

	@Override
	public List<JobDetail> getAllDetails() {

		return jdRepo.findAll();
	}

	@Override
	public JobDetail create(String username, JobDetail jobDetail, int jobAppId) {
						
		Optional<JobApplication> ja = jobAppRepo.findById(jobAppId);
			
		if(ja.isPresent()) {
			JobApplication jobApplication = ja.get();
			jobDetail = jdRepo.saveAndFlush(jobDetail);
			jobApplication.setDetail(jobDetail);
			jobAppRepo.saveAndFlush(jobApplication);
			return jobDetail;
			
		} else {
			return null;
		}

		
	}

	@Override
	public JobDetail update(String username, JobDetail jobDetail, int jobDetailId) {
	
		Optional <JobDetail> opt = jdRepo.findById(jobDetailId);
		if(opt.isPresent()) {
			JobDetail dbJobDetail = opt.get();
			
			dbJobDetail.setComment(jobDetail.getComment());
			dbJobDetail.setFinishDate(jobDetail.getFinishDate());
			dbJobDetail.setStartDate(jobDetail.getStartDate());
			dbJobDetail.setRating(jobDetail.getRating());
			
			
			return jdRepo.saveAndFlush(dbJobDetail);
			
		} else {
			return null;
		}
		
	}

	@Override
	public boolean delete(String username, int id) {
		
		Optional <JobDetail> opt = jdRepo.findById(id);
		
		if(opt.isPresent()) {
			JobDetail jobDetail = opt.get();
			JobApplication jobApp = jobDetail.getApplication();
			jobApp.setDetail(null);
			jobAppRepo.save(jobApp);
			
			jdRepo.delete(jobDetail);
			
			return true;
			
		} else {
			
			return false;
		}
	}

}
