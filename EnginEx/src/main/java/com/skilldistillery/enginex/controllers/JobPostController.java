package com.skilldistillery.enginex.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	private String username = "jacob";
	
	@GetMapping(path = "jobs")
	public List<JobPost> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		System.out.println("in controller");
		return jobPostServ.index();
	}

}
