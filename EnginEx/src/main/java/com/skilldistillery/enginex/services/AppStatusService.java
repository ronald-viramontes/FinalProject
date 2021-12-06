package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.AppStatus;

public interface AppStatusService {
	
	List<AppStatus> getAllAppStatus();
	AppStatus appStatusById(int statusId);
	
}
