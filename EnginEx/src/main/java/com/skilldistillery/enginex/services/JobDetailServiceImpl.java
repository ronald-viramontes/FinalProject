package com.skilldistillery.enginex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.JobDetail;
import com.skilldistillery.enginex.entities.JobPost;
import com.skilldistillery.enginex.repositories.JobDetailRepository;
import com.skilldistillery.enginex.repositories.JobPostRepository;
import com.skilldistillery.enginex.repositories.UserRepository;

@Service
public class JobDetailServiceImpl implements JobDetailService {

	@Autowired
	private JobDetailRepository jdRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JobPostRepository jobPostRepo;
	
	@Override
	public JobDetail getJobDetailById(int id) {

		return jdRepo.findById(id);
	}

	@Override
	public List<JobDetail> getAllDetails() {

		return jdRepo.findAll();
	}

	@Override
	public JobDetail create(String username, JobDetail jobDetail, int jobPostId) {
//		User user = userRepo.findByUsername(username);

			Optional<JobPost> jp = jobPostRepo.findById(jobPostId);
			if(jp.isPresent()) {
				JobPost jobPost = jp.get();
				jobDetail.getApplication().setJobPost(jobPost);
				jdRepo.saveAndFlush(jobDetail);
				return jobDetail;
				
			} else {
				return null;
			}

		
	}

	@Override
	public JobDetail update(String username, JobDetail jobDetail, int jobDetailId) {
	
		JobDetail jd = jdRepo.findById(jobDetail.getId());
	
		if(jd != null) {
			
			jdRepo.save(jobDetail);
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
