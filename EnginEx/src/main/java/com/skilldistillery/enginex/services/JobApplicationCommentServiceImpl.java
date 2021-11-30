package com.skilldistillery.enginex.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.JobApplication;
import com.skilldistillery.enginex.entities.JobApplicationComment;
import com.skilldistillery.enginex.entities.User;
import com.skilldistillery.enginex.repositories.JobApplicationCommentRepository;
import com.skilldistillery.enginex.repositories.JobApplicationRepository;
import com.skilldistillery.enginex.repositories.UserRepository;
@Service
public class JobApplicationCommentServiceImpl implements JobApplicationCommentService {
	@Autowired
	private JobApplicationCommentRepository commentRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private JobApplicationRepository appRepo;
	
	@Override
	public List<JobApplicationComment> findAll() {
		return commentRepo.findAll();
	}

//	@Override
//	public List<JobApplicationComment> findByUserId(String username, int userId) {
//		User user = userRepo.findByUsername(username);
//		
//		List<JobApplicationComment> allUserComments = new ArrayList<>();
//		int count = 0;
//		if(user.getId() == userId) {
//			for(JobApplication apps: user.getApplications()) {
//				allUserComments.add(count, apps.getComments());
//				
//				
//			}
//			
//			return allUserComments;
//		}
//		return null;
//	}
	
	@Override
	public List<JobApplicationComment> findByUserIdAndAppId(String username, int userId, int appId){
		User user = userRepo.findByUsername(username);
		if(user.getId() == userId) {
		return commentRepo.findByApplication_Id(appId);
		}
		return null;
	}
	
	
	
	@Override
	public JobApplicationComment findByCommentId(String username, int userId, int commentId){
		User user = userRepo.findByUsername(username);
		if(user.getId() == userId) {
			return commentRepo.findById(commentId);
		}
		return null;
	}
	
	
	@Override
	public JobApplicationComment create(String username, JobApplicationComment comment, int userId, int appId) {
		User user = userRepo.findByUsername(username);
		LocalDate localDate = LocalDate.now();
		
		JobApplication opt = appRepo.findById(appId);
		
		if(user.getId() == userId && opt != null) {
			
			JobApplicationComment dbComment = new JobApplicationComment();
			dbComment.setApplication(opt);
			dbComment.setComment(comment.getComment());
			dbComment.setDate(localDate);
			dbComment = commentRepo.saveAndFlush(dbComment);
			return dbComment;
		}
		return null;
	}
	
	@Override
	public JobApplicationComment createReply(String username, JobApplicationComment comment, int userId, int appId, int commentId) {
		User user = userRepo.findByUsername(username);
		LocalDate localDate = LocalDate.now();
		JobApplicationComment replyToComment = commentRepo.findById(commentId); 
		
		JobApplication opt = appRepo.findById(appId);
		
		if(user.getId() == userId && opt != null) {
			JobApplicationComment dbComment = new JobApplicationComment();
			dbComment.setApplication(opt);
			dbComment.setComment(comment.getComment());
			dbComment.setBaseComment(replyToComment);
			dbComment.setDate(localDate);
			dbComment = commentRepo.saveAndFlush(dbComment);
			return dbComment;
		}
		return null;
	}	

	@Override
	public void delete(String username, int commentId, int appId, int userId) {
		User user = userRepo.findByUsername(username);
		JobApplicationComment opt = commentRepo.findById(commentId);
		JobApplicationComment deleted = null;
		if(user.getId() == userId && opt.getApplication().getId() == appId) {
			deleted = opt;
			deleted.setComment(null);
			commentRepo.saveAndFlush(deleted);
		}
		
	}

	@Override
	public void deleteAppCommentsForJob(String username, int appId) {
//		User user = userRepo.findByUsername(username);	
		List<JobApplicationComment> jobComments = commentRepo.findByApplication_Id(appId);
		for(JobApplicationComment comment: jobComments) {
			commentRepo.delete(comment);
		}
		
	}

	
	
	@Override
	public JobApplicationComment edit(String username, JobApplicationComment comment, int commentId, int appId, int userId) {
		User user = userRepo.findByUsername(username);
		LocalDate localDate = LocalDate.now();
		JobApplicationComment dbComment = commentRepo.findById(commentId);
		if(user.getId() == userId && dbComment != null) {
			
			dbComment.setComment(comment.getComment());
			dbComment.setBaseComment(comment.getBaseComment());
			dbComment.setDate(localDate);
			dbComment = commentRepo.saveAndFlush(dbComment);
			return dbComment;
		}
		return null;
	}


}
