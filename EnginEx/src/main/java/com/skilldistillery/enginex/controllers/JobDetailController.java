package com.skilldistillery.enginex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.enginex.entities.JobDetail;
import com.skilldistillery.enginex.services.JobDetailService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class JobDetailController {

	@Autowired
	private JobDetailService jobPostSvc;
	
	
	@GetMapping("jobdetails")
	public List<JobDetail> getAllJobDetails(){
		
		return jobPostSvc.getAllDetails();
	}
	
	
	
	
	
	
}
