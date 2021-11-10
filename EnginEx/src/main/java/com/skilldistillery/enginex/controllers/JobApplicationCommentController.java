package com.skilldistillery.enginex.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.enginex.entities.JobApplicationComment;
import com.skilldistillery.enginex.repositories.UserRepository;
import com.skilldistillery.enginex.services.JobApplicationCommentService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class JobApplicationCommentController {

	@Autowired
	private JobApplicationCommentService commentSvc;
	@Autowired
	private UserRepository userRepo;

	@GetMapping("comments")
	public List<JobApplicationComment> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		return commentSvc.findAll();
	}

//	@GetMapping("comments/{userId}")
//	public List<JobApplicationComment> getByUserId(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId,
//			Principal principal) {
//		
//		List<JobApplicationComment> comments = commentSvc.findByUserId(principal.getName(), userId);
//		if(comments != null) {
//			res.setStatus(200);
//			return comments;
//		} else {
//			res.setStatus(404);
//			return null;
//		}
//		
//		
//	}
	
	@GetMapping("comments/users/{userId}/applications/{appId}/{commentId}")
	public JobApplicationComment getCommentById(HttpServletRequest req, HttpServletResponse res, 
												@PathVariable int userId, @PathVariable int appId,
												 @PathVariable int commentId, Principal principal) {
	
		JobApplicationComment comment = commentSvc.findByCommentId(principal.getName(), userId, commentId);
		if(comment != null) {
			res.setStatus(200);
			return comment;
		} else {
			res.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("comments/users/{userId}/applications/{appId}")
	public List<JobApplicationComment> getByUserIdAndAppId(HttpServletRequest req, HttpServletResponse res, 
														   @PathVariable int userId,  @PathVariable int appId,
														   Principal principal) {
		
		List<JobApplicationComment> comments = commentSvc.findByUserIdAndAppId(principal.getName(), userId, appId);
		if(comments != null) {
			res.setStatus(200);
			return comments;
		} else {
			res.setStatus(404);
			return null;
		}
		
		
	}

	@PostMapping("comments/{userId}/{appId}")
	public JobApplicationComment create(HttpServletRequest req, HttpServletResponse res,
			@PathVariable int appId, @PathVariable int userId, @RequestBody JobApplicationComment comment, Principal principal) {
		
		comment = commentSvc.create(principal.getName(), comment, userId, appId);
		
		if(comment != null) {
			res.setStatus(200);
			return comment;
		} else {
			res.setStatus(400);
			return null;
		}
		
	}
	@PostMapping("comments/{userId}/{appId}/{commentId}")
	public JobApplicationComment createReply(HttpServletRequest req, HttpServletResponse res,
											 @PathVariable int userId, @PathVariable int appId, 
											 @PathVariable int commentId, @RequestBody JobApplicationComment comment, 
											 Principal principal) {
		comment = commentSvc.createReply(principal.getName(), comment, userId, appId, commentId);
		
		if(comment != null) {
			res.setStatus(200);
			return comment;
		} else {
			res.setStatus(400);
			return null;
		}
		
	}
	
	@DeleteMapping("comments/{appId}/{commentId}")
	public void delete(HttpServletRequest req, HttpServletResponse res,
			@PathVariable int appId, @PathVariable int commentId, Principal principal) {
		int userId = userRepo.findByUsername(principal.getName()).getId();
		commentSvc.delete(principal.getName(), commentId, appId, userId);
	}

	@PutMapping("comments/{appId}/{commentId}")
	public JobApplicationComment edit(HttpServletRequest req, HttpServletResponse res,
			@PathVariable int appId, @PathVariable int commentId, @RequestBody JobApplicationComment comment,  Principal principal) {
		
		int userId = userRepo.findByUsername(principal.getName()).getId();
		comment = commentSvc.edit(principal.getName(), comment, commentId, appId, userId);
		if(comment != null) {
			res.setStatus(200);
			return comment;
		} else {
			res.setStatus(400);
			return null;
		}
	}

}
