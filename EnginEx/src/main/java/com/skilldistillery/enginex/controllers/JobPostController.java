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
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class JobPostController {

	@Autowired
	private JobPostService jobPostServ;


	@GetMapping(path = "jobs")
	public List<JobPost> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		System.out.println("in controller");
		return jobPostServ.index();
	}

	@GetMapping(path = "jobs/{id}")
	public JobPost show(@PathVariable int id, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		return jobPostServ.show(id);
	}

	@PostMapping(path = "jobs")
	public JobPost create(@RequestBody JobPost jobPost) {
		jobPost.setDatePosted(LocalDate.now());
		return jobPostServ.create(jobPost);
	}

	@DeleteMapping(path = "jobs/{id}")
	public void delete(@PathVariable int id, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		jobPostServ.destroy(id);
	}

	@PutMapping(path = "jobs/{id}")
	public JobPost update(@PathVariable int id, @RequestBody JobPost jobPost, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		jobPost = jobPostServ.update(id, jobPost);
		return jobPost;
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

}
