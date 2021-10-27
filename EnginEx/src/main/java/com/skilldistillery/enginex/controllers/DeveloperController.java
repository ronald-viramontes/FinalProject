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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.enginex.entities.Developer;
import com.skilldistillery.enginex.repositories.UserRepository;
import com.skilldistillery.enginex.services.DeveloperService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class DeveloperController {

	@Autowired
	private DeveloperService devSvc;
	@Autowired
	private UserRepository userRepo;

	@GetMapping("developers")
	public List<Developer> index(HttpServletRequest req, HttpServletResponse res) {
		return devSvc.findAll();
	}

	@GetMapping("developers/{dId}")
	public Developer show(HttpServletRequest req, HttpServletResponse res, @PathVariable int dId) {
		Developer dev = devSvc.findById(dId);
		if (dev != null) {
			return dev;
		} else {
			res.setStatus(404);
			return null;
		}
	}

	@PutMapping("developers/{dId}")
	public Developer edit(HttpServletRequest req, HttpServletResponse res, @RequestBody Developer edit,
			@PathVariable int dId, Principal principal) {
		int userId = userRepo.findByUsername(principal.getName()).getId();
		return devSvc.edit(edit, userId, dId);
	}
	
	@DeleteMapping("developers/{dId}")
	public boolean delete(HttpServletRequest req, HttpServletResponse res, 
			@PathVariable int dId, Principal principal) {
		return devSvc.delete(principal.getName(), dId);
	}

}
