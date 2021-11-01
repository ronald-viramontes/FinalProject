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

import com.skilldistillery.enginex.entities.DeveloperEducation;
import com.skilldistillery.enginex.services.EducationService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class EducationController {

	@Autowired
	EducationService edSvc;
	
	@GetMapping("educations")
	public List<DeveloperEducation> index(HttpServletRequest req, HttpServletResponse res, Principal principal){
		return edSvc.index();
	}
	
	@GetMapping("educations/{uId}")
	public List<DeveloperEducation> getByDevId(HttpServletRequest req, HttpServletResponse res, @PathVariable int uId){
		return edSvc.findByDevId(uId);
	}
	
	@PostMapping("educations/{uId}")
	public DeveloperEducation create(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody DeveloperEducation edu, @PathVariable int uId) {
		System.out.println("---------------------------------------------------------------------------- -----------------------------------------------------------------");
		return edSvc.create(uId, edu, principal.getName());
	}
	
	@PutMapping("educations/{uId}/{eId}")
	public DeveloperEducation edit(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody DeveloperEducation edu, @PathVariable int uId, @PathVariable int eId) {
		edu = edSvc.edit(uId, edu, principal.getName(), eId);
		if(edu != null) {
			return edu;
		}
		else {
			res.setStatus(403);
			return null;
		}
	}
	
	@DeleteMapping("educations/{uId}/{eId}")
	public void delete(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable int uId, @PathVariable int eId) {
		edSvc.delete(uId, principal.getName(), eId);
	}
}
 