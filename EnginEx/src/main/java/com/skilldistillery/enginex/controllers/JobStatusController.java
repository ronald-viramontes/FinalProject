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
	
	@PostMapping("jobstatus/{jobPostId}")
	public JobStatus createJobStatus(@PathVariable Integer jobPostId, 
										@RequestBody JobStatus jobStatus,
										HttpServletRequest req, 
										HttpServletResponse res,
										Principal principal) {
		
		jobStatus = jobStatusSvc.create(jobStatus, principal.getName(), jobPostId);
		
		if(jobStatus == null) {
			res.setStatus(400);
			return null;
		} else {
			res.setStatus(200);
			return jobStatus;
		}
		
	}
	
	@PutMapping("jobstatus/{jobStatusId}")
	public JobStatus updateJobStatus(@PathVariable Integer jobStatusId,
											@RequestBody JobStatus jobStatus,
											HttpServletRequest req, 
											HttpServletResponse res,
											Principal principal) {
		
		jobStatus = jobStatusSvc.update(jobStatus, principal.getName(), jobStatusId);
		
		if(jobStatus == null) {
			res.setStatus(400);
			return null;
		} else {
			res.setStatus(200);
			return jobStatus;
		}
		
	}
	
	@DeleteMapping("jobposts/{jobPostId}/jobstatus/{jobStatusId}")
	public void deleteJobStatus(@PathVariable Integer jobStatusId,
											@PathVariable Integer jobPostId,
											HttpServletRequest req, 
											HttpServletResponse res,
											Principal principal) {
		
		if(jobStatusSvc.destroy(principal.getName(), jobStatusId, jobPostId)) {
			res.setStatus(200);
		} else {
			res.setStatus(400);
		}
		
	}
	
	
	
	
}
