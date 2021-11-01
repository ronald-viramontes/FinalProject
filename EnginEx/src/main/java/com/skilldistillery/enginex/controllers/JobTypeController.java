package com.skilldistillery.enginex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.enginex.entities.JobType;
import com.skilldistillery.enginex.services.JobTypeService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class JobTypeController {

	@Autowired
	private JobTypeService jobTypeServ;
	
	@GetMapping("jobtypes")
	private List<JobType> index() {
		return jobTypeServ.getall();
	}
}
