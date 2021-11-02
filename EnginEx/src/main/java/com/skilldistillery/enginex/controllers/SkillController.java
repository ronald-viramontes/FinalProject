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
import com.skilldistillery.enginex.services.SkillService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class SkillController {

	@Autowired
	private SkillService skillSvc;

	@GetMapping("skills")
	public List<DeveloperSkill> index(){
		return skillSvc.index();
	}
	
	@GetMapping("skills/{userId}")
	public List<DeveloperSkill> skillsByDevId(HttpServletRequest req, 
											  HttpServletResponse res, 
											  Principal principal,
											  @PathVariable int userId){
		
		List<DeveloperSkill> skills = skillSvc.findByDevId(userId, principal.getName());
		if(skills.size() < 0 ) {
			res.setStatus(400);
			return null;
		} else {
			res.setStatus(200);
			return skills;
		}
		
	}
	
	@PostMapping("skills")
	public DeveloperSkill create(HttpServletRequest req, 
								 			HttpServletResponse res, 
								 			Principal principal, 
								 			@RequestBody DeveloperSkill skill) {
		
		skill = skillSvc.create(skill, principal.getName());
		
		if(skill == null) {
			res.setStatus(400);
			return null;
		} else {
			res.setStatus(200);
			return skill;
		}
		

	}
	
	@PutMapping("skills/{userId}/{sId}")
	public DeveloperSkill edit(HttpServletRequest req, 
										HttpServletResponse res, 
										Principal principal, 
										@RequestBody DeveloperSkill edit, 
										@PathVariable Integer sId,
										@PathVariable Integer userId) {
		
		
		edit = skillSvc.edit(userId, edit, principal.getName(), sId);
		if(edit == null) {
			res.setStatus(400);
			return null;
		} else {
			res.setStatus(200);
			return edit;
		}
	}
	
	
	@DeleteMapping("skills/{userId}/{sId}")
	public void delete(HttpServletRequest req, HttpServletResponse res, 
									Principal principal, 
									@PathVariable Integer sId,
									@PathVariable Integer userId) {
		
		skillSvc.delete(userId, principal.getName(), sId);
	}
}
