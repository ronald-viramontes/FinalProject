package com.skilldistillery.enginex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.enginex.entities.AppStatus;
import com.skilldistillery.enginex.services.AppStatusService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class AppStatusController {

	@Autowired
	private AppStatusService appStatSvc;
	
	@GetMapping("appstats")
	public List<AppStatus> index(){
		return appStatSvc.getAllAppStatus();
	}
	
	@GetMapping("appstats/{statusId}")
	public AppStatus findAppStatusById(@PathVariable int statusId){
		return appStatSvc.appStatusById(statusId);
	}
	
	
}
