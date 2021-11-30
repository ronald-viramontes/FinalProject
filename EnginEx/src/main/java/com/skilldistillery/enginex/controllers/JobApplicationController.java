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
	public List<JobApplication> getByDevId(HttpServletRequest req, HttpServletResponse res, 
										   @PathVariable int userId, Principal principal) {
		return appSvc.findByDevId(userId);
	}

	
	
	@GetMapping("apps/app/{appId}")
	public JobApplication getByAppId(HttpServletRequest req, HttpServletResponse res, @PathVariable int appId,
			Principal principal) {
		return appSvc.findByAppId(appId);
	}

	@PostMapping("apps/{postId}/{userId}")
	public JobApplication create(HttpServletRequest req, HttpServletResponse res,
			@PathVariable int postId, @PathVariable int userId) {
		return appSvc.create(userId, postId);

	}

	
	@PutMapping("apps/{appId}/{statusId}")
	public JobApplication edit(@RequestBody JobApplication app, HttpServletRequest req, HttpServletResponse res,
			@PathVariable int appId, @PathVariable int statusId, Principal principal) {
		int userId = userRepo.findByUsername(principal.getName()).getId();
		return app = appSvc.edit(statusId, appId, userId );
	}
	
	@DeleteMapping("apps/{appId}")
	public boolean delete(HttpServletRequest req, HttpServletResponse res,
			@PathVariable int appId, Principal principal) {
		int userId = userRepo.findByUsername(principal.getName()).getId();
		return appSvc.delete(appId, userId);
	}

	@GetMapping("userapps/app/{appId}")
	public JobApplication applicationById(HttpServletRequest req, HttpServletResponse res, 
										   @PathVariable int appId,	Principal principal) {
		
		JobApplication app = appSvc.findByAppId(appId);
		if(app == null) {
			res.setStatus(400);
			return null;
		} else {
			res.setStatus(200);
			return app;
		}
		
	}

	@GetMapping("userapps/{userId}")
	public List<JobApplication> userApps(HttpServletRequest req, HttpServletResponse res, 
										 @PathVariable int userId, Principal principal) {
		
		List<JobApplication> apps = appSvc.findAppsByUser(principal.getName() , userId);
		
		if(apps.size() < 0) {
			res.setStatus(400);
			return null;
		} else {
			res.setStatus(200);
			return apps;
		}
	}
	
	
	
	@PostMapping("userapps/new/{postId}")
	public JobApplication createNewApplication(HttpServletRequest req, HttpServletResponse res,
											   @PathVariable Integer postId, Principal principal ) {
		
		JobApplication app = appSvc.createApp(principal.getName(), postId);
		
		if(app == null) {
			res.setStatus(400);
			return null;
		} else {
			res.setStatus(200);
			return app;
		}
		
	}


	@PostMapping("userapps")
	public JobApplication createApp(@RequestBody JobApplication app, HttpServletRequest req, 
									HttpServletResponse res, Principal principal) {
		
		app.setDate(LocalDate.now());
		app = appSvc.submitApplication(principal.getName(), app);
		
		if(app == null) {
			res.setStatus(400);
			return null;
		} else {
			res.setStatus(200);
			return app;
		}
	}
	

	@PutMapping("userapps/denied/{appId}")
	public JobApplication appDeclined(@RequestBody JobApplication app,  HttpServletRequest req, HttpServletResponse res,
									   @PathVariable Integer appId,
									   Principal principal) {
		app = appSvc.findByAppId(appId);
		
		if (app != null) {
					
			app.setDecisionDate(LocalDate.now());
			app.setApproved(false);
			app.setStatus("Application Closed");
			app = appSvc.appDecision(principal.getName(), app);
		}
			if(app == null) {
				res.setStatus(400);
				return null;
			} else {
				res.setStatus(200);
				return app;
			}
		
		
		
	}

	@PutMapping("userapps/approved/{appId}")
	public JobApplication appApproved(@RequestBody JobApplication app, HttpServletRequest req, HttpServletResponse res,
									 @PathVariable Integer appId, 
									  Principal principal) {
		app = appSvc.findByAppId(appId);
		
		if (app != null) {
					
			app.setDecisionDate(LocalDate.now());
			app.setApproved(true);
			app.setStatus("Application Approved");
			app = appSvc.appDecision(principal.getName(), app);
		}
			if(app == null) {
				res.setStatus(400);
				return null;
			} else {
				res.setStatus(200);
				return app;
			}
		
		
	}

	@DeleteMapping("userapps/{appId}")
	public void deleteApplication(HttpServletRequest req, HttpServletResponse res,
								  @PathVariable Integer appId, Principal principal) {
		
		boolean result = appSvc.destroyApp(principal.getName(), appId);
			if(result == false) {
				res.setStatus(404);
			} else {
				res.setStatus(200);
				
			}
		
	}

}
