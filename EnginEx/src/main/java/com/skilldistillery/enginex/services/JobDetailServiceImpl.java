package com.skilldistillery.enginex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.JobApplication;
import com.skilldistillery.enginex.entities.JobDetail;
import com.skilldistillery.enginex.repositories.JobApplicationRepo;
import com.skilldistillery.enginex.repositories.JobDetailRepository;

@Service
public class JobDetailServiceImpl implements JobDetailService {

	@Autowired
	private JobDetailRepository jdRepo;
	
	@Autowired
	private JobApplicationRepo jobAppRepo;
	
	@Override
	public JobDetail getJobDetailById(int id) {

		return jdRepo.findById(id);
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
			jobApplication.setDetail(jobDetail);
			jobAppRepo.save(jobApplication);
			return jobDetail;
			
		} else {
			return null;
		}

		
	}

	@Override
	public JobDetail update(String username, JobDetail jobDetail, int jobAppId) {
	
		JobDetail jd = jdRepo.findById(jobDetail.getId());
	
		if(jd != null) {
			
			jobDetail = jdRepo.save(jobDetail);
			return jobDetail;
			
		} else {
			return null;
		}
		
	}

	@Override
	public boolean delete(String username, int id) {
		JobDetail jd = jdRepo.findById(id);
		if(jd != null) {
			jdRepo.delete(jd);
			return true;
		} else {
			
			return false;
		}
	}

}
