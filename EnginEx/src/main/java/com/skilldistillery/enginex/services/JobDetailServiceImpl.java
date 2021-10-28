//package com.skilldistillery.enginex.services;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.skilldistillery.enginex.entities.JobDetail;
//import com.skilldistillery.enginex.entities.JobPost;
//import com.skilldistillery.enginex.entities.User;
//import com.skilldistillery.enginex.repositories.JobDetailRepository;
//import com.skilldistillery.enginex.repositories.JobPostRepository;
//import com.skilldistillery.enginex.repositories.UserRepository;
//
//@Service
//public class JobDetailServiceImpl implements JobDetailService {
//
//	@Autowired
//	private JobDetailRepository jdRepo;
//	
//	@Autowired
//	private UserRepository userRepo;
//	
//	@Autowired
//	private JobPostRepository jobPostRepo;
//	
//	@Override
//	public JobDetail getJobDetailById(int id) {
//
//		return jdRepo.findById(id);
//	}
//
//	@Override
//	public List<JobDetail> getAllDetails() {
//
//		return jdRepo.findAll();
//	}
//
//	@Override
//	public JobDetail create(String username, JobDetail jobDetail, int jobPostId) {
//		User user = userRepo.findByUsername(username);
//		
//		JobPost jobPost = jobPostRepo.findById(jobPostId);
//		
//		if(jobDetail.getApplication().getJobPost() == jobPost && user.getClient().getId() == jobPost.getClient().getId() ) {
//			
//			jdRepo.saveAndFlush(jobDetail);
//			return jobDetail;
//	
//		}	else {
//			return null;
//		}
//		
//		
//	}
//
//	@Override
//	public JobDetail update(String username, JobDetail jobDetail, int jobPostId) {
//
//		return null;
//	}
//
//	@Override
//	public boolean delete(String username, int id) {
//		return false;
//	}
//
//}
