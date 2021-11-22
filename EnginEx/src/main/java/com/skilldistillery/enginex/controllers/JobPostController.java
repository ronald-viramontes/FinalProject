package com.skilldistillery.enginex.controllers;

import java.security.Principal;
import java.time.LocalDate;
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

import com.skilldistillery.enginex.entities.JobPost;
import com.skilldistillery.enginex.services.JobPostService;

@RestController
@CrossOrigin({ "*", "http://localhost:4300" })
@RequestMapping("api")
public class JobPostController {

	@Autowired
	private JobPostService jobPostServ;


	@GetMapping(path = "jobs")
	public List<JobPost> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		System.out.println("in controller");
		return jobPostServ.index();
	}

	@GetMapping(path = "jobs/{postId}")
	public JobPost show(@PathVariable Integer postId, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		
		JobPost jobPost = jobPostServ.show(postId);
		
		if(jobPost == null) {
			res.setStatus(400);
			return null;
		} else {
			res.setStatus(200);
			return jobPost;
		}		
	}
	
	@GetMapping("jobs/user/{userId}")
	public List<JobPost> jobsByClientId (HttpServletResponse res, HttpServletRequest req, @PathVariable int userId){
		return jobPostServ.findByClientId(userId);
	}
	
	@GetMapping("jobs/status/{status}")
	public List<JobPost> jobsByStatus(HttpServletRequest req, HttpServletResponse res, @PathVariable String status){
		return jobPostServ.findByStatus(status);
	}
	
	@GetMapping("jobs/search/{keyword}")
	public List<JobPost> findByKeyword (HttpServletRequest req, HttpServletResponse res, @PathVariable String keyword){
		return jobPostServ.findByKeyword(keyword);
	}

	@PostMapping("jobs")
	public JobPost create(@RequestBody JobPost jobPost) {
		
		jobPost.setDatePosted(LocalDate.now());
		
		return jobPost = jobPostServ.create(jobPost);
		
	}
		
	@PutMapping("jobs/{id}")
	public JobPost update(@PathVariable int id, @RequestBody JobPost jobPost, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		jobPost = jobPostServ.update(id, jobPost);
		return jobPost;
	}

	@DeleteMapping(path = "jobs/{id}")
	public void delete(@PathVariable int id, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		jobPostServ.destroy(id);
	}
	
	@PostMapping("userjobs")
	public JobPost createAPost(@RequestBody JobPost jobPost, HttpServletRequest req, 
							   HttpServletResponse res, Principal principal) {
			
			
			jobPost = jobPostServ.createPost(principal.getName(), jobPost);
			
			if(jobPost == null) {
				res.setStatus(400);
				return null;
			} else {
				res.setStatus(200);
				return jobPost;
			}		
	}
	
	@PutMapping("userjobs/{postId}")
	public JobPost updateMyPost(@RequestBody JobPost jobPost, @PathVariable Integer postId, 
								HttpServletRequest req, HttpServletResponse res, Principal principal) {
				
		jobPost = jobPostServ.updatePost(principal.getName(), jobPost, postId);
		
		if(jobPost == null) {
			res.setStatus(400);
			return null;
		} else {
			res.setStatus(200);
			return jobPost;
		}
	}

	@DeleteMapping("userjobs/{postId}")
	public void deleteMyPost(@PathVariable Integer postId, HttpServletRequest req, 
							 HttpServletResponse res, Principal principal) {
		
		boolean result = jobPostServ.destroyMyPost(principal.getName(), postId);
		
		if(result == false) {
			res.setStatus(404);
			
		} else {
			res.setStatus(200);
			
		}
		
	}

}
