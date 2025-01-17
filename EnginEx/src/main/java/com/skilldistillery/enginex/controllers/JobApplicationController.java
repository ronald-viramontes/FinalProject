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

import com.skilldistillery.enginex.entities.AppStatus;
import com.skilldistillery.enginex.entities.JobApplication;
import com.skilldistillery.enginex.repositories.AppStatusRepository;
import com.skilldistillery.enginex.repositories.UserRepository;
import com.skilldistillery.enginex.services.JobApplicationService;

@CrossOrigin({ "*", "http://localhost:4300" })
@RestController
@RequestMapping("api")
public class JobApplicationController {

	@Autowired
	private JobApplicationService appSvc;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AppStatusRepository appStatRepo;
	
	@GetMapping(path = "apps")
	public List<JobApplication> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		return appSvc.findAll();
	}

	@GetMapping(path = "apps/{userId}")
	public List<JobApplication> getByDevId(HttpServletRequest req, HttpServletResponse res, 
										   @PathVariable int userId, Principal principal) {
		return appSvc.findByDevId(userId);
	}

	
	
	@GetMapping(path = "apps/app/{appId}")
	public JobApplication getByAppId(HttpServletRequest req, HttpServletResponse res, @PathVariable int appId,
			Principal principal) {
		return appSvc.findByAppId(appId);
	}

	@PostMapping(path = "apps/{postId}/{userId}")
	public JobApplication create(HttpServletRequest req, HttpServletResponse res,
								 @PathVariable int postId, @PathVariable int userId) {
		return appSvc.create(userId, postId);

	}

	
	@PutMapping(path = "apps/{appId}/{statusId}")
	public JobApplication edit(@RequestBody JobApplication app, HttpServletRequest req, HttpServletResponse res,
			@PathVariable int appId, @PathVariable int statusId, Principal principal) {
		int userId = userRepo.findByUsername(principal.getName()).getId();
		return app = appSvc.edit(statusId, appId, userId );
	}
	
	@DeleteMapping(path = "apps/{appId}")
	public boolean delete(HttpServletRequest req, HttpServletResponse res,
			@PathVariable int appId, Principal principal) {
		int userId = userRepo.findByUsername(principal.getName()).getId();
		return appSvc.delete(appId, userId);
	}

	@GetMapping(path = "userapps/app/{appId}")
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

	@GetMapping(path = "userapps/{userId}")
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
	
	
	
	@PostMapping(path = "userapps/new/{postId}")
	public JobApplication createNewApplication(HttpServletRequest req, HttpServletResponse res,
											   @PathVariable Integer postId, Principal principal ) {
		
		JobApplication app = appSvc.createApp(principal.getName(), postId);
		
		return app;
		
	}


//	@PostMapping("userapps")
//	public JobApplication createApp(@RequestBody JobApplication app, HttpServletRequest req, 
//									HttpServletResponse res, Principal principal) {
//		
//		app.setDate(LocalDate.now());
//		app = appSvc.submitApplication(principal.getName(), app);
//		
//		if(app == null) {
//			res.setStatus(400);
//			return null;
//		} else {
//			res.setStatus(200);
//			return app;
//		}
//	}
//	
	@CrossOrigin({ "*", "http://localhost:4300" })
	@PutMapping(path = "/userapps/denied/{appId}")
	public JobApplication appDeclined(HttpServletRequest req, HttpServletResponse res,
									   @PathVariable int appId,
									   Principal principal) {
		
	JobApplication	app = appSvc.findByAppId(appId);
		AppStatus appStatus = appStatRepo.findById(4);
			app.setDecisionDate(LocalDate.now());
			app.setAppStatus(appStatus);
			app = appSvc.appDecision(principal.getName(), app);
			return app;
	}

	@PutMapping(path = "/userapps/approved/{appId}")
	public JobApplication appApproved(HttpServletRequest req, HttpServletResponse res,
									 @PathVariable int appId, 
									  Principal principal) {
		JobApplication app = appSvc.findByAppId(appId);
		AppStatus appStatus = appStatRepo.findById(3);
			app.setDecisionDate(LocalDate.now());
			app.setAppStatus(appStatus);
			app = appSvc.appDecision(principal.getName(), app);
			return app;
		
		
	}

	@DeleteMapping(path = "userapps/{appId}")
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
