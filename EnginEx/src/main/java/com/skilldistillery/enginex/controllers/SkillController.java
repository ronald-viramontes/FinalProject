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

import com.skilldistillery.enginex.entities.DeveloperSkill;
import com.skilldistillery.enginex.repositories.UserRepository;
import com.skilldistillery.enginex.services.SkillService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class SkillController {

	@Autowired
	private SkillService skillSvc;
	@Autowired
	private UserRepository userRepo;

	@GetMapping("skills")
	public List<DeveloperSkill> index(){
		return skillSvc.findAll();
	}
	
	@GetMapping("skills/{dId}")
	public List<DeveloperSkill> skillsByDevId(@PathVariable int dId){
		return skillSvc.findByDevId(dId);
	}
	
	@PostMapping("skills")
	public DeveloperSkill create(HttpServletRequest req, HttpServletResponse res, 
			Principal principal, @RequestBody DeveloperSkill newSkill) {
		return skillSvc.create(newSkill, principal.getName());
	}
	
	@PutMapping("skills/{sId}")
	public DeveloperSkill edit(HttpServletRequest req, HttpServletResponse res, 
			Principal principal, @RequestBody DeveloperSkill edit, @PathVariable int sId) {
		int userId = userRepo.findByUsername(principal.getName()).getId();
		return skillSvc.edit(edit, userId, sId);
	}
	
	
	@DeleteMapping("skills/{sId}")
	public boolean delete(HttpServletRequest req, HttpServletResponse res, 
			Principal principal, @PathVariable int sId) {
		int userId = userRepo.findByUsername(principal.getName()).getId();
		return skillSvc.delete(sId, userId);
	}
}
