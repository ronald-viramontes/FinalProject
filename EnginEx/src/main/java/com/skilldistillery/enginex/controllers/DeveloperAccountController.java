package com.skilldistillery.enginex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.enginex.services.DeveloperService;


@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class DeveloperAccountController {
	
	@Autowired
	private DeveloperService devServ;
	
	@GetMapping("ping")
	public String ping() {
		return "pong";
	}
	

}
