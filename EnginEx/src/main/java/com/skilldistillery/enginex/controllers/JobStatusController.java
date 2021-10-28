package com.skilldistillery.enginex.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.enginex.entities.JobStatus;
import com.skilldistillery.enginex.services.JobStatusService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class JobStatusController {

	@Autowired
	private JobStatusService jobStatusSvc;
	
	@GetMapping("jobstatus")
	public List<JobStatus> index(){
		return jobStatusSvc.getAll();
	}
	
	@GetMapping("jobstatus/{jobStatusId}")
	public JobStatus getJobStatusById(@PathVariable Integer jobStatusId,
										HttpServletRequest req, 
										HttpServletResponse res) {
		JobStatus jobStatus = jobStatusSvc.getById(jobStatusId);
		if(jobStatus == null) {
			res.setStatus(404);
			return null;
		} else {
			res.setStatus(200);
			return jobStatus;
		}
		
	}
	
	
	
	
}
