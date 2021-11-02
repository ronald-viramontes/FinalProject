package com.skilldistillery.enginex.controllers;

import java.security.Principal;

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

import com.skilldistillery.enginex.entities.JobDetail;
import com.skilldistillery.enginex.services.JobDetailService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class JobDetailController {

	@Autowired
	private JobDetailService jobDetailSvc;
	
		
	@GetMapping("jobdetails/{jobDetailId}")
	public JobDetail getJobDetailById(@PathVariable Integer jobDetailId, 
												HttpServletResponse res) {
			
		JobDetail jobDetail = jobDetailSvc.getJobDetailById(jobDetailId);
		if(jobDetail == null) {
			res.setStatus(404);
			return null;
		} else {
			res.setStatus(200);
			return jobDetail;
			
		}
		
	}
	
	@GetMapping("jobdetails/applications/{appId}")
	public JobDetail getJobDetailByAppId(@PathVariable Integer appId,
													HttpServletResponse res, 
													Principal principal) {
		
		JobDetail jobDetail = jobDetailSvc.getJobDetailByAppId(appId, principal.getName());
		if(jobDetail == null) {
			res.setStatus(400);
			return null;
		}	else {
			res.setStatus(202);
			return jobDetail;
		}
		
		
	}
	
	
	@PostMapping("jobdetails/{jobAppId}")
	public JobDetail createJobDetail(@PathVariable Integer jobAppId,
									@RequestBody JobDetail jobDetail,
									HttpServletResponse res, 
									Principal principal) {
		
		jobDetail = jobDetailSvc.create(principal.getName(), jobDetail, jobAppId);
		
		if(jobDetail == null) {
			res.setStatus(400);
			return null;
		}	else {
			res.setStatus(202);
			return jobDetail;
		}
		
	}
	
	@PutMapping("jobdetails/{userId}/{jobDetailId}")
	public JobDetail updateJobDetail(@PathVariable Integer jobDetailId,
									 @PathVariable Integer userId,
									 @RequestBody JobDetail jobDetail,
									 HttpServletResponse res, 
									 Principal principal) {

		
		
		jobDetail = jobDetailSvc.update(principal.getName(), userId, jobDetail, jobDetailId);
				
		if(jobDetail == null) {
			res.setStatus(400);
			return null;
		}	else {
			res.setStatus(202);
			return jobDetail;
		}
		
	}
	
	@DeleteMapping("jobdetails/{userId}/{jobDetailId}")
	public void deleteJobDetail(@PathVariable Integer jobDetailId,
								@PathVariable Integer userId,
											
											Principal principal) {
		
		jobDetailSvc.delete(principal.getName(), userId, jobDetailId); 
		
		
	}
	
	
	
	
	
}
