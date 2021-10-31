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
	
	@GetMapping("educations/{dId}")
	public List<DeveloperEducation> getByDevId(HttpServletRequest req, HttpServletResponse res, @PathVariable int dId){
		return edSvc.findByDevId(dId);
	}
	
	@PostMapping("educations/{dId}")
	public DeveloperEducation create(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody DeveloperEducation edu, @PathVariable int dId) {
		return edSvc.create(dId, edu, principal.getName());
	}
	
	@PutMapping("educations/{dId}/{eId}")
	public DeveloperEducation edit(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody DeveloperEducation edu, @PathVariable int dId, @PathVariable int eId) {
		return edSvc.edit(dId, edu, principal.getName(), eId);
	}
	
	
	@DeleteMapping("educations/{dId}/{eId}")
	public void delete(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable int dId, @PathVariable int eId) {
		edSvc.delete(dId, principal.getName(), eId);
	}
}
 