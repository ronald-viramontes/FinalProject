package com.skilldistillery.enginex.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.AppStatus;
import com.skilldistillery.enginex.repositories.AppStatusRepository;

@Service
public class AppStatusServiceImpl implements AppStatusService{
	
	@Autowired
	private AppStatusRepository appStatRepo;

	@Override
	public List<AppStatus> getAllAppStatus() {

		return appStatRepo.findAll();
	}

	@Override
	public AppStatus appStatusById(int statusId) {
		return appStatRepo.findById(statusId);
	}
	
	


}
