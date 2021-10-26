package com.skilldistillery.enginex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.enginex.services.DeveloperAccountService;


@RestController
@RequestMapping("api")
public class DeveloperAccountController {
	
	@Autowired
	private DeveloperAccountService devrServ;
	
	@GetMapping("ping")
	public String ping() {
		return "pong";
	}
	

}
