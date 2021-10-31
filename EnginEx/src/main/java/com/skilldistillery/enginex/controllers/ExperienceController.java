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

import com.skilldistillery.enginex.entities.WorkExperience;
import com.skilldistillery.enginex.services.ExperienceService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class ExperienceController {

	@Autowired
	ExperienceService expSvc;
	
	@GetMapping("experiences")
	public List<WorkExperience> index(HttpServletRequest req, 
									  HttpServletResponse res, 
									  Principal principal){
		return expSvc.index();
	}
	
	@GetMapping("experiences/{devId}")
	public List<WorkExperience> getByDevId(HttpServletRequest req, 
											HttpServletResponse res, 
											Principal principal, 
											@PathVariable int devId){
		
		List<WorkExperience> exps = expSvc.findByDevId(devId, principal.getName());
		if(exps.size() < 0 ) {
			res.setStatus(400);
			return null;
		} else {
			res.setStatus(200);
			return exps;
		}
	}
	
	@PostMapping("experiences")
	public WorkExperience create(HttpServletRequest req, 
								 HttpServletResponse res, 
								 Principal principal, 
								 @RequestBody WorkExperience exp
								 ) {
		
		exp = expSvc.create(exp, principal.getName());
		
		if(exp == null) {
			res.setStatus(400);
			return null;
		} else {
			res.setStatus(200);
			return exp;
		}
		
	}
	
	@PutMapping("experiences/{devId}/{expId}")
	public WorkExperience edit(HttpServletRequest req, 
							   HttpServletResponse res, 
							   Principal principal, 
							   @RequestBody WorkExperience exp, 
							   @PathVariable Integer devId, 
							   @PathVariable Integer expId) {
		
		exp = expSvc.edit(devId, exp, principal.getName(), expId);
		if(exp == null) {
			res.setStatus(400);
			return null;
		} else {
			res.setStatus(200);
			return exp;
		}
	}
	
	
	@DeleteMapping("experiences/{devId}/{expId}")
	public void delete(HttpServletRequest req, 
					   HttpServletResponse res, 
					   Principal principal, 
					   @PathVariable int devId, 
					   @PathVariable int expId) {
		
		expSvc.delete(devId, principal.getName(), expId);
        
	}
}
 