package com.skilldistillery.enginex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	
	public List<DeveloperSkill> index(){
		return skillSvc.findAll();
	}
}
