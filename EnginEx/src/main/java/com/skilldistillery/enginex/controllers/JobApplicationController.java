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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.enginex.entities.JobApplication;
import com.skilldistillery.enginex.repositories.UserRepository;
import com.skilldistillery.enginex.services.JobApplicationService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class JobApplicationController {

	@Autowired
	private JobApplicationService appSvc;
	@Autowired
	private UserRepository userRepo;

	@GetMapping("apps")
	public List<JobApplication> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		return appSvc.findAll();
	}

	@GetMapping("apps/{userId}")
	public List<JobApplication> getByDevId(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId,
			Principal principal) {
		return appSvc.findByDevId(userId);
	}
	
	@GetMapping("apps/app/{appId}")
	public JobApplication getByAppId(HttpServletRequest req, HttpServletResponse res, @PathVariable int appId,
			Principal principal) {
		return appSvc.findByAppId(appId);
	}

	
	
	@PostMapping("apps/{pId}/{uId}")
	public JobApplication create(HttpServletRequest req, HttpServletResponse res,
			@PathVariable int pId, @PathVariable int uId) {
		return appSvc.create(uId, pId);

	}
	
	@DeleteMapping("apps/{aId}")
	public boolean delete(HttpServletRequest req, HttpServletResponse res,
			@PathVariable int aId, Principal principal) {
		int userId = userRepo.findByUsername(principal.getName()).getId();
		return appSvc.delete(aId, userId);
	}

	@PutMapping("apps/{aId}/{statusId}")
	public JobApplication edit(HttpServletRequest req, HttpServletResponse res,
			@PathVariable int aId, @PathVariable int statusId, Principal principal) {
		int userId = userRepo.findByUsername(principal.getName()).getId();
		return appSvc.edit(statusId, aId, userId);
	}

}
